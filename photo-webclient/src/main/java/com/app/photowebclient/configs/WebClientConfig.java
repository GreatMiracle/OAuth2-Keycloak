package com.app.photowebclient.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(value = {WebClientProperties.class})
public class WebClientConfig implements BeanFactoryAware {
    private BeanFactory beanFactory;
    private final WebClientProperties webClientProperties;

    public WebClientConfig(WebClientProperties webClientProperties) {
        this.webClientProperties = webClientProperties;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @PostConstruct
    public void onPostConstruct() {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
        List<WebClientProperties.WebClient> webClientBeans = webClientProperties.getWebclients();
        for (WebClientProperties.WebClient webClientBean : webClientBeans) {
            // setup beans programmatically
            String beanName = webClientBean.getBeanName();
            Map<String, String> headers = webClientBean.getHeaders();

            WebClient webClient = WebClient.builder()
                    .baseUrl(webClientBean.getBaseUrl())
                    .defaultHeaders(httpHeaders -> headers.forEach(httpHeaders::add)).build(); //TODO: set token into header

            configurableBeanFactory.registerSingleton(beanName, webClient);
        }
    }

    @Bean
    public WebClient webClient(ClientRegistrationRepository clientRegistrationrepository,
                               OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
    ) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationrepository,
                        oAuth2AuthorizedClientRepository);

        oauth2.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder().apply(oauth2.oauth2Configuration()).build();
    }
}
