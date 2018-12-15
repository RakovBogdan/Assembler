package org.bogdanrakov.assembler;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AssemblerTest {

    Assembler testInstance = new Assembler(new ArrayList<String>());

    @Test
    public void convertDecimalToBinary() {
        String result = testInstance.convertDecimalToBinary("13", 4);

        assertEquals("1101", result);
    }
}