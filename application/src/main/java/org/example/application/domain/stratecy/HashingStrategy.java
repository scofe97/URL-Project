package org.example.application.domain.stratecy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.application.domain.stratecy.hashing.Md5HashingService;
import org.example.application.domain.stratecy.hashing.Sha256HashingService;

@Getter
@RequiredArgsConstructor
public enum HashingStrategy {
    MD5(new Md5HashingService()),
    SHA256(new Sha256HashingService());

    private final HashingService service;

    public static HashingService fromString(String method) {
        try {
            return valueOf(method.toUpperCase()).service;
        } catch (IllegalArgumentException e) {
            return MD5.service; // 기본 전략
        }
    }
}
