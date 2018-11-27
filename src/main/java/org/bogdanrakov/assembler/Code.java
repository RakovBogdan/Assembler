package org.bogdanrakov.assembler;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Code {

    private static Map<String, String> computeToBinary = Stream.of(
            new AbstractMap.SimpleEntry<>("0", "0101010"),
            new AbstractMap.SimpleEntry<>("1", "0111111"),
            new AbstractMap.SimpleEntry<>("-1", "0111010"),
            new AbstractMap.SimpleEntry<>("D", "0001100"),
            new AbstractMap.SimpleEntry<>("A", "0110000"),
            new AbstractMap.SimpleEntry<>("!D", "0001101"),
            new AbstractMap.SimpleEntry<>("!A", "0110001"),
            new AbstractMap.SimpleEntry<>("-D", "0001111"),
            new AbstractMap.SimpleEntry<>("-A", ""),
            new AbstractMap.SimpleEntry<>("D+1", ""),
            new AbstractMap.SimpleEntry<>("A+1", ""),
            new AbstractMap.SimpleEntry<>("D-1", ""),
            new AbstractMap.SimpleEntry<>("A-1", ""),
            new AbstractMap.SimpleEntry<>("D+A", ""),
            new AbstractMap.SimpleEntry<>("D-A", ""),
            new AbstractMap.SimpleEntry<>("A-D", ""),
            new AbstractMap.SimpleEntry<>("D&A", ""),

            new AbstractMap.SimpleEntry<>("M", ""),
            new AbstractMap.SimpleEntry<>("!M", ""),
            new AbstractMap.SimpleEntry<>("-M", ""),
            new AbstractMap.SimpleEntry<>("M+1", ""),
            new AbstractMap.SimpleEntry<>("M-1", ""),
            new AbstractMap.SimpleEntry<>("D+M", ""),
            new AbstractMap.SimpleEntry<>("D-M", ""),
            new AbstractMap.SimpleEntry<>("M-D", ""),
            new AbstractMap.SimpleEntry<>("D&M", ""),
            new AbstractMap.SimpleEntry<>("D|M", ""))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    private static Map<String, String> jumpToBinary = new HashMap<>();
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
