package org.example.adapterin.controller;

import lombok.RequiredArgsConstructor;
import org.example.adapterin.dto.UrlCreateDto;
import org.example.application.port.in.UrlUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/urls")
@RequiredArgsConstructor
public class UrlController {
    private final UrlUseCase urlUseCase;

    @PostMapping
    public ResponseEntity<String> shortenUrl(@RequestBody UrlCreateDto shortenedCreateDto) {
        String shortenUrl = urlUseCase.shortenUrl(
                shortenedCreateDto.originalUrl(),
                shortenedCreateDto.hashingMethod()
        );
        return ResponseEntity.ok(shortenUrl);
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortKey) {
        String originalUrl = urlUseCase.getOriginalUrl(shortKey);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(originalUrl))
                .build();
    }

}
