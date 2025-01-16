package org.example.practice.application.domain.stratecy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.practice.application.domain.stratecy.encoding.Base62EncodingService;
import org.example.practice.application.domain.stratecy.encoding.Base64EncodingService;

@Getter
@RequiredArgsConstructor
public enum EncodingStrategy {

    BASE62(new Base62EncodingService()),
    BASE64(new Base64EncodingService());

    private final EncodingService service;

    public static EncodingService fromString(String method) {
        try {
            return valueOf(method.toUpperCase()).service;
        } catch (IllegalArgumentException e) {
            return BASE62.service;
        }
    }
}
