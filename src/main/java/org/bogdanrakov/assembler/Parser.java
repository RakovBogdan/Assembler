package org.bogdanrakov.assembler;

import java.io.File;
import java.util.List;

class Parser {
    private List<String> lines;
    private String currentCommand;
    private int currentLineIndex = -1;

    Parser(List<String> lines) {
        this.lines = lines;
    }

    boolean hasMoreLines() {
        return currentLineIndex < lines.size() - 1;
    }

    void advance() {
        currentLineIndex++;
        currentCommand = lines.get(currentLineIndex);
    }

    CommandType commandType() {
        return CommandType.A_COMMAND;
    }

    String symbol() {
        return null;
    }

    String destination() {
        return null;
    }

    String compute() {
        return null;
    }

    String jump() {
        return null;
    }
}
