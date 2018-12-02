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
            new AbstractMap.SimpleEntry<>("-A", "0110011"),
            new AbstractMap.SimpleEntry<>("D+1", "0011111"),
            new AbstractMap.SimpleEntry<>("A+1", "0110111"),
            new AbstractMap.SimpleEntry<>("D-1", "0001110"),
            new AbstractMap.SimpleEntry<>("A-1", "0110010"),
            new AbstractMap.SimpleEntry<>("D+A", "0000010"),
            new AbstractMap.SimpleEntry<>("D-A", "0010011"),
            new AbstractMap.SimpleEntry<>("A-D", "0000111"),
            new AbstractMap.SimpleEntry<>("D&A", "0000000"),
            new AbstractMap.SimpleEntry<>("D|A", "0010101"),

            new AbstractMap.SimpleEntry<>("M", "1110000"),
            new AbstractMap.SimpleEntry<>("!M", "0110001"),
            new AbstractMap.SimpleEntry<>("-M", "1110011"),
            new AbstractMap.SimpleEntry<>("M+1", "1110111"),
            new AbstractMap.SimpleEntry<>("M-1", "1110010"),
            new AbstractMap.SimpleEntry<>("D+M", "1000010"),
            new AbstractMap.SimpleEntry<>("D-M", "1010011"),
            new AbstractMap.SimpleEntry<>("M-D", "1000111"),
            new AbstractMap.SimpleEntry<>("D&M", "1000000"),
            new AbstractMap.SimpleEntry<>("D|M", "1010101")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    private static Map<String, String> jumpToBinary = Stream.of(
            new AbstractMap.SimpleEntry<>("JGT", "001"),
            new AbstractMap.SimpleEntry<>("JEQ", "010"),
            new AbstractMap.SimpleEntry<>("JGE", "011"),
            new AbstractMap.SimpleEntry<>("JLT", "100"),
            new AbstractMap.SimpleEntry<>("JNE", "101"),
            new AbstractMap.SimpleEntry<>("JLE", "110"),
            new AbstractMap.SimpleEntry<>("JMP", "111")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    private static Map<String, String> destinationToBinary = Stream.of(
            new AbstractMap.SimpleEntry<>("A", "100"),
            new AbstractMap.SimpleEntry<>("D", "010"),
            new AbstractMap.SimpleEntry<>("M", "001"),
            new AbstractMap.SimpleEntry<>("AD", "110"),
            new AbstractMap.SimpleEntry<>("AM", "101"),
            new AbstractMap.SimpleEntry<>("DM", "001"),
            new AbstractMap.SimpleEntry<>("AMD", "111")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


    public static String destination(String destinationMnemonic) {
        return destinationToBinary.get(destinationMnemonic);
    }

    public static String compute(String computeMnemonic) {
        return computeToBinary.get(computeMnemonic);
    }

    public static String jump(String jumpMnemonic) {
        return jumpToBinary.get(jumpMnemonic);
    }
}
