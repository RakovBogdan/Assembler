package org.bogdanrakov.assembler;

import java.io.File;
import java.util.List;

class Parser {
    private List<String> lines;

    Parser(List<String> lines) {
        this.lines = lines;
    }

    boolean hasMoreLines() {
        return false;
    }

    void advance() {

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
