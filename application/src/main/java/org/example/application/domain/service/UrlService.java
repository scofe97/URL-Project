package org.example.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.application.domain.model.Url;
import org.example.application.domain.stratecy.HashingStrategy;
import org.example.application.domain.stratecy.HashingService;
import org.example.application.port.in.UrlUseCase;
import org.example.application.port.out.UrlRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService implements UrlUseCase {

    private final UrlRepositoryPort urlRepositoryPort;

    @Override
    public String shortenUrl(String originalUrl, String hashingMethod) {
        String domain = extractDomain(originalUrl);
        String path = extractPath(originalUrl);

        HashingService hashingService = HashingStrategy.fromString(hashingMethod);
        String shortKey = hashingService.hash(originalUrl).substring(0, 8);

        int attempt = 1;
        while (urlRepositoryPort.existsByShortenKey(shortKey)) {
            String newShortKey = path + attempt;
            shortKey = hashingService.hash(newShortKey).substring(0, 8);
            attempt++;
        }

        urlRepositoryPort.save( Url.builder()
                .originalUrl(originalUrl)
                .shortKey(shortKey)
                .createdAt(LocalDateTime.now())
                .hashingMethod(hashingMethod)
                .build());

        return domain + "/" + shortKey;
    }

    @Override
    public String getOriginalUrl(String shortKey) {
        Url url = urlRepositoryPort.findByShortKey(shortKey)
                .orElseThrow(() -> new RuntimeException("단축 URL 값을 찾지 못했습니다. shortKey: " + shortKey));

        return url.getOriginalUrl();
    }

    private String extractDomain(String url) {
        int domainIndex = url.indexOf("/", url.indexOf("//") + 2);
        return domainIndex == -1 ? url : url.substring(domainIndex);
    }

    private String extractPath(String url) {
        int pathIndex = url.indexOf("/", url.indexOf("//") + 2);
        return pathIndex == -1 ? url : url.substring(pathIndex);
    }

}
