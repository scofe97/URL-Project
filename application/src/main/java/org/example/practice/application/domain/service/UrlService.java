package org.example.practice.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.practice.application.domain.model.Url;
import org.example.practice.application.domain.stratecy.HashingStrategy;
import org.example.practice.application.domain.stratecy.HashingService;
import org.example.practice.application.port.in.UrlUseCase;
import org.example.practice.application.port.out.UrlRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService implements UrlUseCase {

    private final UrlRepositoryPort urlRepositoryPort;

    @Override
    public String shortenUrl(String originalUrl, String hashingMethod) {
        String domain = extractDomain();
        String path = extractPath(originalUrl);

        HashingService hashingService = HashingStrategy.fromString(hashingMethod);
        String shortKey = hashingService.hash(originalUrl).substring(0, 8);

        int attempt = 1;
        while (urlRepositoryPort.existsByShortenedUrl(shortKey)) {
            String newShortKey = path + attempt;
            shortKey = hashingService.hash(newShortKey).substring(0, 8);
            attempt++;
        }

        urlRepositoryPort.save( Url.builder()
                .originalUrl(originalUrl)
                .shortenedUrl(shortKey)
                .createdAt(LocalDateTime.now())
                .build());

        return domain + "/" + shortKey;
    }

    @Override
    public String getOriginalUrl(String shortenedUrl) {
        String hashUrl = extractPath(shortenedUrl);

        Url url = urlRepositoryPort.findByShortenedUrl(hashUrl)
                .orElseThrow(() -> new RuntimeException("단축 URL 값을 찾지 못했습니다. shortenedUrl: " + shortenedUrl));

        return url.getOriginalUrl();
    }

    private String extractDomain() {
        return "http://localhost:8080";
    }

    private String extractPath(String url) {
        int startIndex = url.indexOf("/", url.indexOf("//") + 2);
        return startIndex == -1 ? url : url.substring(startIndex + 1);
    }

}
