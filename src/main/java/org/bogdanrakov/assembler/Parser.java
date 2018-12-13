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
        return currentCommand.substring(1, currentCommand.length() - 1);
    }

    String destination() {
        String destination = null;
        int destinationCommandEnd = currentCommand.indexOf("=");
        if (destinationCommandEnd != -1) {
            destination = currentCommand.substring(0, destinationCommandEnd);
        }
        return destination;
    }

    String compute() {
        String compute = null;
        int destinationCommandEnd = currentCommand.indexOf("=");
        int jumpCommandStart = currentCommand.indexOf(";");
        if (jumpCommandStart != -1 && destinationCommandEnd != -1) {
            compute = currentCommand.substring(destinationCommandEnd + 1, jumpCommandStart);
        } else if (jumpCommandStart != -1) {
            compute = currentCommand.substring(0, jumpCommandStart);
        } else {
            compute = currentCommand.substring(destinationCommandEnd + 1);
        }
        return compute;
    }

    String jump() {
        String jump = null;
        int jumpCommandStart = currentCommand.indexOf(";");
        if (jumpCommandStart != -1) {
            jump = currentCommand.substring(jumpCommandStart + 1);
        }
        return jump;
    }

    public String getCurrentCommand() {
        return currentCommand;
    }

    public int getCurrentLineIndex() {
        return currentLineIndex;
    }
}
