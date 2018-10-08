package org.bogdanrakov.assembler;

import java.util.HashMap;
import java.util.Map;

public class Code {

    private static Map<String, String> mnemonicToBinary = new HashMap<>();

    public static String destination(String destinationMnemonic) {
        return destinationMnemonic;
    }

    public static String compute(String computeMnemonic) {
        return computeMnemonic;
    }

    public static String jump(String jumpMnemonic) {
        return jumpMnemonic;
    }
}
