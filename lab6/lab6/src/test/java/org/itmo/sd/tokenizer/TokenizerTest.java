package org.itmo.sd.tokenizer;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.itmo.sd.tokenizer.token.binary.BinOp.*;
import static org.itmo.sd.tokenizer.token.bracket.BracketEnum.CLOSE_BRACKET;
import static org.itmo.sd.tokenizer.token.bracket.BracketEnum.OPEN_BRACKET;
import static org.junit.Assert.assertEquals;

public class TokenizerTest {
    @Test
    public void simpleTest() throws IOException {
        String in = "(1 + 2) / 3";
        InputStream inputStream = new ByteArrayInputStream(in.getBytes());
        List<Token> actual = new Tokenizer(inputStream).getTokenList();

        List<Token> expected =
                List.of(
                        OPEN_BRACKET,
                        new Digit(1),
                        SUM_OP,
                        new Digit(2),
                        CLOSE_BRACKET,
                        DIV_OP,
                        new Digit(3));

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void hardTest() throws IOException {
        String in = "(10(1 + 21*22  )) / 33+32";
        InputStream inputStream = new ByteArrayInputStream(in.getBytes());
        List<Token> actual = new Tokenizer(inputStream).getTokenList();

        List<Token> expected =
                List.of(
                        OPEN_BRACKET,
                        new Digit(10),
                        OPEN_BRACKET,
                        new Digit(1),
                        SUM_OP,
                        new Digit(21),
                        MUL_OP,
                        new Digit(22),
                        CLOSE_BRACKET,
                        CLOSE_BRACKET,
                        DIV_OP,
                        new Digit(33),
                        SUM_OP,
                        new Digit(32));

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
