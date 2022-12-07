package me.talk.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final WebClient webClient;
    private LocalDateTime accessTokenExpire;
    private String refreshToken;
    @Value("${login.token}")
    private String loginToken;
    @Value("${grant.type}")
    private String grantType;
    @Value("${client.id}")
    private String clientId;
    @Value("${client.secret}")
    private String clientSecret;
    @Value("${client.scope}")
    private String clientScope;


    public String getAccessToken() {
        if (accessTokenExpire != null && LocalDateTime.now().isAfter(accessTokenExpire)) {
            return refreshAccessToken();
        } else {
            accessTokenExpire = LocalDateTime.now();
            Auth auth = getAuth().block();
            if (auth != null) {
                accessTokenExpire = accessTokenExpire.plusSeconds(auth.getExpires_in());
                refreshToken = auth.getRefresh_token();
                return auth.getAccess_token();
            } else {
                throw new RuntimeException("Something wrong");
            }
        }
    }

    private Mono<Auth> getAuth() {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", grantType);
        map.add("client_id", clientId);
        map.add("scope", clientScope);
        return webClient.post()
                .uri("/token/")
                .headers(httpHeaders -> httpHeaders
                        .setBasicAuth(loginToken))
                .body(BodyInserters.fromMultipartData(map))
                .retrieve()
                .bodyToMono(Auth.class);
    }

    private String refreshAccessToken() {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", grantType);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("refresh_token", refreshToken);
        Mono<Auth> mono = webClient.post()
                .uri("/token/")
                .headers(httpHeaders -> httpHeaders
                        .setBasicAuth(loginToken))
                .body(BodyInserters.fromMultipartData(map))
                .retrieve()
                .bodyToMono(Auth.class);
        accessTokenExpire = LocalDateTime.now();
        Auth auth = mono.block();
        if (auth != null) {
            accessTokenExpire = accessTokenExpire.plusSeconds(auth.getExpires_in());
            refreshToken = auth.getRefresh_token();
            return auth.getAccess_token();
        } else {
            throw new RuntimeException("Something wrong");
        }

    }
}
