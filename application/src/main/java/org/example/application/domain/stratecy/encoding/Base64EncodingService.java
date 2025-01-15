package org.example.application.domain.stratecy.encoding;

import org.example.application.domain.stratecy.EncodingService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64EncodingService implements EncodingService {
    @Override
    public String encode(String input) {
        return Base64.getUrlEncoder()                                   // URL-safe Base64 인코더 생성
                .withoutPadding()                                       // '=' 패딩 제거 (URL에서 불필요한 문자 제거)
                .encodeToString(input.getBytes(StandardCharsets.UTF_8)) // 입력 문자열을 UTF-8 바이트로 변환 후 Base64로 인코딩
                .substring(0, 8);                                       // 결과 문자열의 앞 8글
    }
}
