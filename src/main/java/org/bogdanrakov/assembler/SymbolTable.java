package org.bogdanrakov.assembler;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SymbolTable {

    private int nextVariableAddress = 16;

    private Map<String, Integer> symbolTable = Stream.of(
            new AbstractMap.SimpleEntry<>("SCREEN", 16384),
            new AbstractMap.SimpleEntry<>("KBD", 25676),
            new AbstractMap.SimpleEntry<>("SP", 0),
            new AbstractMap.SimpleEntry<>("LCL", 1),
            new AbstractMap.SimpleEntry<>("ARG", 2),
            new AbstractMap.SimpleEntry<>("THIS", 3),
            new AbstractMap.SimpleEntry<>("THAT", 4)
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public SymbolTable() {
        populateMapWithRegisters();
    }

    private void populateMapWithRegisters() {
        for (int i = 0; i < 16; i++) {
            symbolTable.put("R" + i, i);
        }
    }

    public void addEntry(String symbol, int address) {
        symbolTable.put(symbol, address);
    }

    public void addEntry(String symbol) {
        symbolTable.put(symbol, nextVariableAddress++);
    }

    public int getAddress(String symbol) {
        return symbolTable.get(symbol);
    }

    public boolean contains(String symbol) {
        return symbolTable.containsKey(symbol);
    }

    public int getNextVariableAddress() {
        return nextVariableAddress;
    }
}
