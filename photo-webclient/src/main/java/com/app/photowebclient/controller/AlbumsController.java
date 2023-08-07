package com.app.photowebclient.controller;

import com.app.photowebclient.configs.WebClientProperties;
import com.oauth2.common.library.dto.response.AlbumRest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

//@RestController
@Slf4j
@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlbumsController {

    OAuth2AuthorizedClientService oauth2ClientService;
    RestTemplate restTemplate;

//    @GetMapping("/albums")
//    public String getAlbums(Model model
//            , @AuthenticationPrincipal OidcUser principal) {
//        // Kiểm tra xem người dùng đã xác thực hay chưa
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated() && principal != null) {
//            OidcIdToken idToken = principal.getIdToken();
//            String tokenValue = idToken.getTokenValue();
//            log.info("========>tokenValue - {}", tokenValue);
//
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", "Bearer " + tokenValue);
//
//            String url = "http://localhost:8092/albums";
//            HttpEntity<List<AlbumRest>> entity = new HttpEntity<>(headers);
//            ResponseEntity<List<AlbumRest>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AlbumRest>>() {
//            });
//
//            List<AlbumRest> albums = responseEntity.getBody();
//
//            model.addAttribute("albums", "albums");
//            return "albums";
//        } else {
//            // Xử lý tùy theo trạng thái xác thực
//            return "redirect:/login";
//        }
//    }


//    @GetMapping("/albums")
//    public String getAlbums(Model model,
//                            @AuthenticationPrincipal OidcUser principal) {
//
//        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
//        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//
//        OAuth2AuthorizedClient oauth2Client = oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),
//                oauthToken.getName());
//
//        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
//        System.out.println("jwtAccessToken = " + jwtAccessToken);
//
//
//        System.out.println("Principal = " + principal);
//
//        OidcIdToken idToken = principal.getIdToken();
//        String idTokenValue = idToken.getTokenValue();
//        System.out.println("idTokenValue = " + idTokenValue);
//
//        String url = "http://localhost:8092/albums";
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + jwtAccessToken);
//
//        HttpEntity<List<AlbumRest>> entity = new HttpEntity<>(headers);
//
//
//        ResponseEntity<List<AlbumRest>> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AlbumRest>>() {});
//
//        List<AlbumRest> albums = responseEntity.getBody();
//
//        model.addAttribute("albums", albums);
//
//        return "albums";
//    }


    WebClient webClient;
    WebClientProperties webClientProperties;
//    WebClientConfigFollowCourse webClientConfigFollowCourse;

    @GetMapping("/albums")
    public String getAlbums(Model model,
                            @AuthenticationPrincipal OidcUser principal) {

        List<AlbumRest> albums = null;
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            OidcIdToken idToken = principal.getIdToken();
            String tokenValue = idToken.getTokenValue();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + tokenValue);

            albums = webClient.get()
//                    .uri(webClientProperties.getWebclients().get(0).getBaseUrl())
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>() {
                    })
                    .block();

        }


        model.addAttribute("albums", albums);


        return "albums";
    }

}

