package org.itmo.sd.tokenizer;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.itmo.sd.visitor.PrintVisitor;
import org.junit.Test;

import java.util.List;

import static org.itmo.sd.tokenizer.token.binary.BinOp.*;
import static org.junit.Assert.assertEquals;

public class PrintVisitorTest {
    @Test
    public void simpleTest() {
        List<Token> exp =
                List.of(
                        new Digit(1),
                        new Digit(2),
                        SUM_OP,
                        new Digit(3),
                        DIV_OP);

        PrintVisitor printVisitor = new PrintVisitor();

        assertEquals("1 2 + 3 /", printVisitor.visitCompoundToken(exp));
    }

    @Test
    public void hardTest() {
        List<Token> exp =
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


        PrintVisitor printVisitor = new PrintVisitor();

        assertEquals("1 2 10 * + 10 - 17 7 * 8 / + 3 / 1 2 + *", printVisitor.visitCompoundToken(exp));
    }
}
