package org.bogdanrakov.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

public class Assembler {

    public static final String HACK_EXTENSION = ".hack";

    public static void main(String[] args) {
        Assembler assembler = new Assembler();

        String assemblyFilename = args[0];

        List<String> lines = FileUtil.readFileLines(assemblyFilename);
        List<String> assemblyResult = assembler.assemble(lines);

        String newHackFileName = FileUtil.changeExtensionInFileName(assemblyFilename, HACK_EXTENSION);
        FileUtil.writeLinesToNewFile(assemblyResult, newHackFileName);
    }

    private List<String> assemble(List<String> mnemonics) {
        Parser parser = new Parser(mnemonics);
        List<String> assemblyResult = new ArrayList<>();

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

        return binary.reverse().toString();
    }

    void recursiveToBinary(int quotient, StringBuffer sb) {
        if (quotient == 0) {
            return;
        }

        int newQuotient = quotient / 2;
        int remainder = quotient % 2;
        sb.append(remainder);
        recursiveToBinary(newQuotient, sb);
    }

}
