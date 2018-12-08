package org.bogdanrakov.assembler;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SymbolTable {

    private Map<String, Integer> symbolTable = Stream.of(
            new AbstractMap.SimpleEntry<>("R0", 0),
            new AbstractMap.SimpleEntry<>("", 1)
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public void addEntry(String symbol, int address) {
        symbolTable.put(symbol, address);
    }

    public int getAddress(String symbol) {
        return symbolTable.get(symbol);
    }

    public boolean contains(String symbol) {
        return symbolTable.containsKey(symbol);
    }
}
