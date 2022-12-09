package org.itmo.sd.tokenizer;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.itmo.sd.visitor.ParserVisitor;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.itmo.sd.tokenizer.token.binary.BinOp.*;
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
                        SUM_OP,
                        new Digit(3),
                        DIV_OP);

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
                        MUL_OP,
                        SUM_OP,
                        new Digit(10),
                        SUB_OP,
                        new Digit(17),
                        new Digit(7),
                        MUL_OP,
                        new Digit(8),
                        DIV_OP,
                        SUM_OP,
                        new Digit(3),
                        DIV_OP,
                        new Digit(1),
                        new Digit(2),
                        SUM_OP,
                        MUL_OP);

        assertEquals(actual.size(), expected.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
