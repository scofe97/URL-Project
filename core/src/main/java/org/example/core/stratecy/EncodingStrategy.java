package org.example.core.stratecy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.core.port.EncodingServicePort;
import org.example.core.stratecy.encoding.Base62EncodingService;
import org.example.core.stratecy.encoding.Base64EncodingService;

@Getter
@RequiredArgsConstructor
public enum EncodingStrategy {

    BASE62(new Base62EncodingService()),
    BASE64(new Base64EncodingService());

    private final EncodingServicePort service;

    public static EncodingServicePort fromString(String method) {
        try {
            return valueOf(method.toUpperCase()).service;
        } catch (IllegalArgumentException e) {
            return BASE62.service;
        }
    }
}
