package com.acme.iot.platform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class IotPlatformApplicationTests {

    @Test
    void contextLoads() {
        // solo verifica que el contexto cargue sin romper CI/CD
    }
}