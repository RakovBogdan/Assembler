package org.bogdanrakov.assembler;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String HACK_EXTENSION = ".hack";

    public static void main(String[] args) {
        String assemblyFilename = args[0];

        List<String> lines = FileReader.readFileLines(assemblyFilename);
        Parser parser = new Parser(lines);

        List<String> parsedLines = new ArrayList<>();
        parsedLines.add("test");

        String newHackFileName = FileReader.changeExtensionInFileName(assemblyFilename, HACK_EXTENSION);
        FileReader.writeLinesToNewFile(parsedLines, newHackFileName);
    }
}
