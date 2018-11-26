package org.bogdanrakov.assembler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

    @Test
    public void testCommandTypeA() {
        Parser parser = new Parser(Stream.of("@123").collect(Collectors.toList()));
        parser.advance();

        assertEquals(CommandType.A_COMMAND, parser.commandType());
    }

    @Test
    public void testCommandTypeL() {
        Parser parser = new Parser(Stream.of("(LABEL)").collect(Collectors.toList()));
        parser.advance();

        assertEquals(CommandType.L_COMMAND, parser.commandType());
    }

    @Test
    public void testCommandTypeC() {
        Parser parser = new Parser(Stream.of("D=D+1").collect(Collectors.toList()));
        parser.advance();

        assertEquals(CommandType.C_COMMAND, parser.commandType());
    }

}