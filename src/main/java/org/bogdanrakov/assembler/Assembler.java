package org.bogdanrakov.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

public class Assembler {

    public List<String> assemble(List<String> mnemonics) {
        Parser parser = new Parser(mnemonics);
        SymbolTable symbolTable = new SymbolTable();
        List<String> assemblyResult = new ArrayList<>();

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

        Parser secondParser = new Parser(mnemonics);

        while (secondParser.hasMoreLines()) {
            secondParser.advance();
            CommandType commandType = secondParser.commandType();
            if (commandType.equals(CommandType.A_COMMAND)) {
                String value = secondParser.getCurrentCommand().substring(1);
                if (isDecimal(value)) {
                    assemblyResult.add("0" + convertDecimalToBinary(value));
                } else {
                    if (!symbolTable.contains(value)) {
                        symbolTable.addEntry(value);
                    }
                    assemblyResult.add("0" + convertDecimalToBinary(String.valueOf(symbolTable.getAddress(value))));
                }
            } else if (commandType.equals(CommandType.C_COMMAND)) {
                String destination = secondParser.destination();
                String destinationBinary = Code.destination(destination);
                String compute = secondParser.compute();
                String computeBinary = Code.compute(compute);
                String jump = secondParser.jump();
                String jumpBinary = Code.jump(jump);

                String computeInstruction = "111" + computeBinary + destinationBinary + jumpBinary;
                assemblyResult.add(computeInstruction);
            }
        }

        return assemblyResult;
    }

    private boolean isDecimal(String value) {
        return value.matches("\\d*");
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
