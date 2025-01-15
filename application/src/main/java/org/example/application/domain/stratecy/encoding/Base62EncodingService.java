package org.example.application.domain.stratecy.encoding;

import org.example.application.domain.stratecy.EncodingService;

public class Base62EncodingService implements EncodingService {

    private static final String BASE62_CHARS =  "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Override
    public String encode(String input) {
        long value = Math.abs(input.hashCode());  // hashCode를 사용해 숫자 값 생성
        StringBuilder encoded = new StringBuilder();

        while (value > 0) {
            int remainder = (int) (value % 62);   // 62로 나눈 나머지로 인덱싱
            encoded.append(BASE62_CHARS.charAt(remainder));
            value /= 62;
        }

        // 8자리로 패딩
        while (encoded.length() < 8) {
            encoded.append(BASE62_CHARS.charAt(0));
        }

        return encoded.reverse().toString();
    }
}
