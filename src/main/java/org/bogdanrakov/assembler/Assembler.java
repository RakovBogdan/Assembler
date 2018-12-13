package org.bogdanrakov.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

public class Assembler {

    private SymbolTable symbolTable;

    private void populateSymbolTable() {
        symbolTable = new SymbolTable();

    }

    public List<String> assemble(List<String> mnemonics) {
        Parser parser = new Parser(mnemonics);
        List<String> assemblyResult = new ArrayList<>();

        for (String mnemonic: mnemonics) {
            while (parser.hasMoreLines()) {
                CommandType commandType = parser.commandType();
                if (commandType.equals(CommandType.L_COMMAND)) {

                }
            }
        }

        while (parser.hasMoreLines()) {
            parser.advance();
            CommandType commandType = parser.commandType();
            if (commandType.equals(CommandType.A_COMMAND)) {
                assemblyResult.add("0" + convertDecimalToBinary(parser.getCurrentCommand().substring(1)));
            } else {
                String destination = parser.destination();
                String destinationBinary = Code.destination(destination);
                String compute = parser.compute();
                String computeBinary = Code.compute(compute);
                String jump = parser.jump();
                String jumpBinary = Code.jump(jump);

                String computeInstruction = "111" + computeBinary + destinationBinary + jumpBinary;
                assemblyResult.add(computeInstruction);
            }
        }

        return assemblyResult;
    }

    String convertDecimalToBinary(String decimal) {
        StringBuffer binary = new StringBuffer();
        int decimalNumber = Integer.parseInt(decimal);
        recursiveToBinary(decimalNumber, binary);

        int missingZeroes = 15 - binary.length();
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
