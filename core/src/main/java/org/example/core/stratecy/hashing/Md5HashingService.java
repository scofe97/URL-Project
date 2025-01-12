package org.example.core.stratecy.hashing;

import org.example.core.port.HashingServicePort;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5HashingService implements HashingServicePort {
    @Override
    public String hash(String input) {
        try{
            // 1. MD5 해싱 알고리즘 초기화
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2. 입력 문자열을 UTF-8 바이트 배열로 변환 후 해싱
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // 3. 해싱된 바이트 배열을 16진수 문자열로 변환하여 반환
            return new BigInteger(1, hashBytes).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 hashing failed", e);
        }
    }
}
