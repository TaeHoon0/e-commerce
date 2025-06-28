package kr.hhplus.be.server.global.utils;

import java.security.SecureRandom;

public class CodeUtil {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "abcdefghijklmnopqrstuvwxyz"
        + "0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int LENGTH = 10;

    public static String generate() {

        StringBuilder sb = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int idx = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(idx));
        }

        return sb.toString();
    }

}
