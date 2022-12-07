package me.talk.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class WebClientImpl {
    private final WebClient webClient;
    private final TokenService tokenService;


    public Mono<Response> getShopList() {
        return webClient.get()
                .uri("/advcampaigns/website/2090016/?limit=2")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(tokenService.getAccessToken()))
                .retrieve()
                .bodyToMono(Response.class);
    }
}
