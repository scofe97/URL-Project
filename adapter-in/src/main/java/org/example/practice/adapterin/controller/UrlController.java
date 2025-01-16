package org.example.practice.adapterin.controller;

import lombok.RequiredArgsConstructor;
import org.example.practice.adapterin.dto.UrlCreateDto;
import org.example.practice.application.port.in.UrlUseCase;
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

    @GetMapping
    public ResponseEntity<String> getOriginalUrl(@RequestParam String shortenedUrl) {
        String originalUrl = urlUseCase.getOriginalUrl(shortenedUrl);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(originalUrl))
                .build();
    }

}
