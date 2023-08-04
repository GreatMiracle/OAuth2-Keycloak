package com.oauth2.resource.photo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig {
    JwtAuthenticationConverter jwtAuthenticationConverter;
    Converter<Jwt, Collection<GrantedAuthority>> jwtConverter; //khởi tạo KeycloakRoleConverter vì trong đó có @component

    public WebSecurityConfig(Converter<Jwt, Collection<GrantedAuthority>> jwtConverter) {
        this.jwtAuthenticationConverter = new JwtAuthenticationConverter();
        this.jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtConverter);
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()

                .requestMatchers(HttpMethod.GET, "/users/status/check")
//                        .hasAuthority("SCOPE_kien")
                .hasRole("develop")
                .anyRequest().authenticated()

//                .oauth2ResourceServer(oauth2 -> oauth2.jwt( jwtConfigurer -> {} ))
                .and()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter)
        ;
        return http.build();
    }
}
