package org.bogdanrakov.assembler;

import org.junit.Test;

import static org.junit.Assert.*;

public class AssemblerTest {

    Assembler testInstance = new Assembler();

    @Test
    public void convertDecimalToBinary() {
        String result = testInstance.convertDecimalToBinary("13");

        assertEquals("1101", result);
    }
}