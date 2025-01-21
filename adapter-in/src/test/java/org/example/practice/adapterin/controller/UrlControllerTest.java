package org.example.practice.adapterin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.practice.adapterin.dto.UrlCreateDto;
import org.example.practice.application.port.in.UrlUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UrlController.class)
@ContextConfiguration(classes = {UrlControllerTest.MockTestConfig.class})
@ImportAutoConfiguration(exclude = {
        WebMvcAutoConfiguration.class,
        CacheAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class
})
class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlUseCase urlUseCase;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UrlController(urlUseCase))
                .build();
    }

    @Nested
    @DisplayName("URL 단축 테스트")
    class ShortenUrlTest {
        @Test
        @DisplayName("URL 단축 요청시 정상적으로 단축된 URL을 반환한다")
        void shortenUrl_Success() throws Exception {
            // given
            String originalUrl = "https://www.example.com";
            String expectedShortenedUrl = "http://short.url/abc123";
            UrlCreateDto request = new UrlCreateDto(originalUrl, "MD5");

            when(urlUseCase.shortenUrl(originalUrl, "MD5"))
                    .thenReturn(expectedShortenedUrl);

            // when & then
            mockMvc.perform(post("/v1/urls")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(content().string(expectedShortenedUrl))
                    .andDo(print());
        }

        @Test
        void getOriginalUrl() throws Exception {
            // given
            String shortenUrl = "abc123";
            String originalUrl = "https://www.example.com";

            when(urlUseCase.getOriginalUrl(shortenUrl))
                    .thenReturn(originalUrl);

            // when & then
            mockMvc.perform(get("/v1/urls")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("shortenedUrl", shortenUrl))
                    .andExpect(status().isMovedPermanently()) // 301 상태 코드 확인
                    .andExpect(header().string("Location", originalUrl)) // Location 헤더 확인
                    .andDo(print());
        }
    }

    @Configuration
    static class MockTestConfig {
        @Bean
        public UrlUseCase urlUseCase() {
            return Mockito.mock(UrlUseCase.class);
        }
    }
}