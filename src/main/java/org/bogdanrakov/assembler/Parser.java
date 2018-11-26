package org.bogdanrakov.assembler;

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
        do {
            currentLineIndex++;
            currentCommand = lines.get(currentLineIndex).replaceAll(" ", "");
            int commentStart = currentCommand.indexOf("//");
            if (commentStart != -1) {
                currentCommand = currentCommand.substring(0, commentStart);
            }
        } while (currentCommand.length() == 0);
    }

    CommandType commandType() {
        CommandType commandType;
        if (currentCommand.startsWith("@")) {
            commandType = CommandType.A_COMMAND;
        } else if (currentCommand.startsWith("(")) {
            commandType = CommandType.L_COMMAND;
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
