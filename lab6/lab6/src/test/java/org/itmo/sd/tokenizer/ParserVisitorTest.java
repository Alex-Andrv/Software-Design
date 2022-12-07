package org.itmo.sd.tokenizer;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.binary.DivOp;
import org.itmo.sd.tokenizer.token.binary.MulOp;
import org.itmo.sd.tokenizer.token.binary.SubOp;
import org.itmo.sd.tokenizer.token.binary.SumOp;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.itmo.sd.visitor.ParserVisitor;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserVisitorTest {
    @Test
    public void simpleTest() throws IOException {
        String in = "(1 + 2) / 3";
        InputStream inputStream = new ByteArrayInputStream(in.getBytes());
        List<Token> tokens = new Tokenizer(inputStream).getTokenList();
        ParserVisitor parserVisitor = new ParserVisitor();

        List<Token> actual = parserVisitor.visitCompoundToken(tokens);

        List<Token> expected =
                List.of(
                        new Digit(1),
                        new Digit(2),
                        new SumOp(),
                        new Digit(3),
                        new DivOp());

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void hardTest() throws IOException {
        String in = "(1 + 2 * 10 - 10 + 17 * 7 / 8) / 3 * (1 + 2)";
        InputStream inputStream = new ByteArrayInputStream(in.getBytes());
        List<Token> tokens = new Tokenizer(inputStream).getTokenList();
        ParserVisitor parserVisitor = new ParserVisitor();

        List<Token> actual = parserVisitor.visitCompoundToken(tokens);

        List<Token> expected =
                List.of(
                        new Digit(1),
                        new Digit(2),
                        new Digit(10),
                        new MulOp(),
                        new SumOp(),
                        new Digit(10),
                        new SubOp(),
                        new Digit(17),
                        new Digit(7),
                        new MulOp(),
                        new Digit(8),
                        new DivOp(),
                        new SumOp(),
                        new Digit(3),
                        new DivOp(),
                        new Digit(1),
                        new Digit(2),
                        new SumOp(),
                        new MulOp());

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
