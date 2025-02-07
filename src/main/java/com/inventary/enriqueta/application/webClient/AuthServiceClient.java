package com.inventary.enriqueta.application.webClient;

import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.inventary.enriqueta.domain.dto.TokenValidation;
import com.inventary.enriqueta.application.webClient.AuthServiceClient;


@Component
public class AuthServiceClient {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public AuthServiceClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder.baseUrl("https://profound-lark-web-maria-enriqueta-dominicci-7da37999.koyeb.app/firebase-users");
    }

    public Mono<TokenValidation> validateToken(String token) {
        return webClientBuilder.build()
                .get()
                .uri("/validate")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(TokenValidation.class);
    }

}
