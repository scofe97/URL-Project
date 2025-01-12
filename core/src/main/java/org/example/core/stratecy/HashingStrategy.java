package org.example.core.stratecy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.core.port.HashingServicePort;
import org.example.core.stratecy.hashing.Md5HashingService;
import org.example.core.stratecy.hashing.Sha256HashingService;

@Getter
@RequiredArgsConstructor
public enum HashingStrategy {
    MD5(new Md5HashingService()),
    SHA256(new Sha256HashingService());

    private final HashingServicePort service;

    public static HashingServicePort fromString(String method) {
        try {
            return valueOf(method.toUpperCase()).service;
        } catch (IllegalArgumentException e) {
            return MD5.service; // 기본 전략
        }
    }
}
