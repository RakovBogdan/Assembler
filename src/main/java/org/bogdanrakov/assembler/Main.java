package org.bogdanrakov.assembler;

import java.util.List;

public class Main {

    private static final String HACK_EXTENSION = ".hack";

    public static void main(String[] args) {

        String assemblyFilename = args[0];

        List<String> lines = FileUtil.readFileLines(assemblyFilename);
        Assembler assembler = new Assembler(lines);
        List<String> assemblyResult = assembler.assemble();

        String newHackFileName = FileUtil.changeExtensionInFileName(assemblyFilename, HACK_EXTENSION);
        FileUtil.writeLinesToNewFile(assemblyResult, newHackFileName);
    }
}
