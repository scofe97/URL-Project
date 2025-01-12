package org.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.UrlCreateDto;
import org.example.core.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/urls")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<String> shortenUrl(@RequestBody UrlCreateDto shortenedCreateDto) {
        String shortenUrl = urlService.shortenUrl(
                shortenedCreateDto.originalUrl(),
                shortenedCreateDto.hashingMethod(),
                shortenedCreateDto.encodingMethod()
        );
        return ResponseEntity.ok(shortenUrl);
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortKey) {
        String originalUrl = urlService.getOriginalUrl(shortKey);
        return ResponseEntity.ok(originalUrl);
    }

}
