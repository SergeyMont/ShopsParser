package me.talk.client;

import me.talk.config.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class WebClientImplTest {
    private WebClientImpl webClient;

    @BeforeEach
    void WebClientInterfaceTest() {
        webClient = new WebClientImpl(new Configuration().webClientWithTimeout()
                , new TokenService(new Configuration().webClientWithTimeout()));
    }

    @Test
    void getShopList() {

        System.out.println(webClient.getShopList().block().toString());
    }
}