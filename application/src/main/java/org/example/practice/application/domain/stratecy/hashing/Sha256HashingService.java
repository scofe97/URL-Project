package org.example.practice.application.domain.stratecy.hashing;

import org.example.practice.application.domain.stratecy.HashingService;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256HashingService implements HashingService {
    @Override
    public String hash(String input) {
        try{
            // 1. SHA-256 해싱 알고리즘 초기화
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 2. 입력 문자열을 UTF-8 바이트 배열로 변환 후 해싱
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // 3. 해싱된 바이트 배열을 16진수 문자열로 변환하여 반환
            return new BigInteger(1, hashBytes).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD% hashing failed", e);
        }
    }
}
