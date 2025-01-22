package org.example.practice.application.domain.service;

import org.example.practice.application.domain.model.Url;
import org.example.practice.application.port.in.UrlUseCase;
import org.example.practice.application.port.out.UrlRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UrlServiceTest.TestConfig.class})
class UrlServiceTest {

    @Autowired
    private UrlUseCase urlUseCase;

    @Autowired
    private UrlRepositoryPort urlRepositoryPort;


    @Test
    void shortenUrl() {
        // given
        String originalUrl = "https://www.example.com/path";
        String hashingMethod = "MD5";
        String expectedDomain = "http://localhost:8080";

        when(urlRepositoryPort.existsByShortenedUrl(any()))
                .thenReturn(false);

        // when
        String shortenedUrl = urlUseCase.shortenUrl(originalUrl, hashingMethod);

        // then
        assertThat(shortenedUrl).startsWith(expectedDomain + "/");
        assertThat(shortenedUrl.substring(expectedDomain.length() + 1)).hasSize(8);
        verify(urlRepositoryPort).existsByShortenedUrl(any());
    }

    @Test
    void getOriginalUrl() {
        // given
        String shortenedUrl = "http://localhost:8080/abc12345";
        String originalUrl = "https://www.example.com/path";
        String hashUrl = "abc12345"; // extractPath 결과값

        Url mockUrl = Url.builder()
                .originalUrl(originalUrl)
                .shortenedUrl(hashUrl)
                .createdAt(LocalDateTime.now())
                .build();

        when(urlRepositoryPort.findByShortenedUrl(hashUrl))
                .thenReturn(Optional.of(mockUrl));

        // when
        String retrievedUrl = urlUseCase.getOriginalUrl(shortenedUrl);

        // then
        assertThat(retrievedUrl).isEqualTo(originalUrl);
        verify(urlRepositoryPort).findByShortenedUrl(hashUrl);
    }

    @Configuration
    @ComponentScan(basePackages = "org.example.practice.application")
    static class TestConfig {
        @Bean
        public UrlRepositoryPort urlRepositoryPort() {
            return Mockito.mock(UrlRepositoryPort.class);
        }
    }
}