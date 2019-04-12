package org.bogdanrakov.assembler;

import java.util.ArrayList;
import java.util.List;

class Assembler {

    private static final int WORD_SIZE = 16;
    private static final String A_COMMAND_BEGINNING = "0";
    private static final String C_COMMAND_BEGINNING = "111";
    private static final String DIGITS_REGEX = "\\d*";
    private SymbolTable symbolTable;
    private Parser parser;

    Assembler(List<String> mnemonics) {
        symbolTable = new SymbolTable();
        parser = new Parser(mnemonics);
    }

    List<String> assemble() {

        populateSymbolTable();

        List<String> assemblyResult = new ArrayList<>();
        parser.reset();

        while (parser.hasMoreLines()) {
            parser.advance();
            CommandType commandType = parser.commandType();
            if (commandType.equals(CommandType.A_COMMAND)) {
                String value = parser.getCurrentCommand().substring(1);
                if (isDecimal(value)) {
                    assemblyResult.add(A_COMMAND_BEGINNING + convertDecimalToBinary(value, WORD_SIZE));
                } else {
                    if (!symbolTable.contains(value)) {
                        symbolTable.addEntry(value);
                    }
                    String address = String.valueOf(symbolTable.getAddress(value));
                    assemblyResult.add(A_COMMAND_BEGINNING
                            + convertDecimalToBinary(address, WORD_SIZE));
                }
            } else if (commandType.equals(CommandType.C_COMMAND)) {
                String destination = parser.destination();
                String destinationBinary = Code.destination(destination);
                String compute = parser.compute();
                String computeBinary = Code.compute(compute);
                String jump = parser.jump();
                String jumpBinary = Code.jump(jump);

                String computeInstruction = C_COMMAND_BEGINNING + computeBinary + destinationBinary + jumpBinary;
                assemblyResult.add(computeInstruction);
            }
        }

        return assemblyResult;
    }

    private void populateSymbolTable() {
        int currentCommand = -1;
        while (parser.hasMoreLines()) {
            parser.advance();
            CommandType commandType = parser.commandType();
            if (commandType.equals(CommandType.L_COMMAND)) {
                symbolTable.addEntry(parser.symbol(), currentCommand + 1);
            } else {
                currentCommand++;
            }
        }
    }

    private boolean isDecimal(String value) {
        return value.matches(DIGITS_REGEX);
    }

    String convertDecimalToBinary(String decimal, int binaryPositions) {
        StringBuffer binary = new StringBuffer();
        int decimalNumber = Integer.parseInt(decimal);
        recursiveToBinary(decimalNumber, binary);

        int missingZeroes = binaryPositions - binary.length() - 1;
        for (int i = 0; i < missingZeroes; i++) {
            binary.append("0");
        }

        return binary.reverse().toString();
    }

    private void recursiveToBinary(int quotient, StringBuffer sb) {
        if (quotient == 0) {
            return;
        }

        int newQuotient = quotient / 2;
        int remainder = quotient % 2;
        sb.append(remainder);
        recursiveToBinary(newQuotient, sb);
    }
}
