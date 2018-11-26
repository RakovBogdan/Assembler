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
        CommandType commandType = null;
        if (currentCommand.startsWith("@")) {
            if (Character.isDigit(currentCommand.charAt(1))) {
                commandType = CommandType.A_COMMAND;
            } else {
                commandType = CommandType.L_COMMAND;
            }
        } else {
            commandType = CommandType.C_COMMAND;
        }

        return commandType;
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
