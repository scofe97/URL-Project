package org.example.core.service;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.Url;
import org.example.core.port.EncodingServicePort;
import org.example.core.port.HashingServicePort;
import org.example.core.port.UrlRepositoryPort;
import org.example.core.stratecy.EncodingStrategy;
import org.example.core.stratecy.HashingStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepositoryPort urlRepositoryPort;


    public String shortenUrl(String originalUrl, String hashingMethod, String encodingMethod) {
        HashingServicePort hashingService = HashingStrategy.fromString(hashingMethod);
        String hashedValue = hashingService.hash(originalUrl);

        EncodingServicePort encodingService = EncodingStrategy.fromString(encodingMethod);
        String shortKey = encodingService.encode(hashedValue);

        if (urlRepositoryPort.findByShortKey(shortKey).isEmpty()) {
            Url url = Url.builder()
                    .originalUrl(originalUrl)
                    .shortKey(shortKey)
                    .createdAt(LocalDateTime.now())
                    .hashingMethod(hashingMethod)
                    .encodingMethod(encodingMethod)
                    .build();

            urlRepositoryPort.save(url);
            return shortKey;
        }

        return originalUrl;
    }

    public String getOriginalUrl(String originalUrl) {
        return "";
    }

}
