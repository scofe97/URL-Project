package org.example.api.adapter;

import lombok.RequiredArgsConstructor;
import org.example.core.service.UrlService;
import org.example.core.usecase.UrlUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlServiceAdapter implements UrlUseCase {
    private final UrlService urlService;

    @Override
    public String shortenUrl(String originalUrl, String hashingMethod, String encodingMethod) {
        return urlService.shortenUrl(originalUrl, hashingMethod, encodingMethod);
    }

    @Override
    public String getOriginalUrl(String shortKey) {
        return urlService.getOriginalUrl(shortKey);
    }

}
