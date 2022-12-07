package org.itmo.sd.tokenizer;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.binary.DivOp;
import org.itmo.sd.tokenizer.token.binary.MulOp;
import org.itmo.sd.tokenizer.token.binary.SumOp;
import org.itmo.sd.tokenizer.token.bracket.ClBracket;
import org.itmo.sd.tokenizer.token.bracket.OpBracket;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TokenizerTest {
    @Test
    public void simpleTest() throws IOException {
        String in = "(1 + 2) / 3";
        InputStream inputStream = new ByteArrayInputStream(in.getBytes());
        List<Token> actual = new Tokenizer(inputStream).getTokenList();

        List<Token> expected =
                List.of(
                        new OpBracket(),
                        new Digit(1),
                        new SumOp(),
                        new Digit(2),
                        new ClBracket(),
                        new DivOp(),
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
                        new OpBracket(),
                        new Digit(10),
                        new OpBracket(),
                        new Digit(1),
                        new SumOp(),
                        new Digit(21),
                        new MulOp(),
                        new Digit(22),
                        new ClBracket(),
                        new ClBracket(),
                        new DivOp(),
                        new Digit(33),
                        new SumOp(),
                        new Digit(32));

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
