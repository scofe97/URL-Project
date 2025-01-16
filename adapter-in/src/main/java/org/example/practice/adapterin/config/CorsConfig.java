package org.example.practice.adapterin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 URL 허용
                        .allowedOrigins("*")         // 모든 Origin 허용
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                        .allowedHeaders("*")                                       // 모든 헤더 허용
                        .exposedHeaders("Location")                                // Swagger에서 리다이렉션 헤더에 접근 가능
                        .allowCredentials(false);                                  // 크리덴셜(쿠키) 미허용
            }
        };
    }
}
