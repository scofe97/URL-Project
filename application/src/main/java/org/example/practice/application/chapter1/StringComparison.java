package org.example.practice.application.chapter1;

public class StringComparison {
    public static void main(String[] args) {
        String str1 = "안녕하세요";
        String str2 = "안녕하세요";
        String str3 = new String("안녕하세요");

        // 동일성 비교
        System.out.println(str1 == str2); // true
        System.out.println(str1 == str3); // false

        // 동등성 비교
        System.out.println(str1.equals(str2)); // true
        System.out.println(str1.equals(str3)); // true
    }


}