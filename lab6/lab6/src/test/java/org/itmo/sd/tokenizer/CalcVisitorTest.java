package org.itmo.sd.tokenizer;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.itmo.sd.visitor.CalcVisitor;
import org.junit.Test;

import java.util.List;

import static org.itmo.sd.tokenizer.token.binary.BinOp.*;
import static org.junit.Assert.assertEquals;

public class CalcVisitorTest {
    @Test
    public void simpleTest() {
        List<Token> exp =
                List.of(
                        new Digit(1),
                        new Digit(2),
                        SUM_OP,
                        new Digit(3),
                        DIV_OP);

        CalcVisitor calcVisitor = new CalcVisitor();

        assertEquals(1, (int) calcVisitor.visitCompoundToken(exp));
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

        CalcVisitor calcVisitor = new CalcVisitor();

        assertEquals(24, (int) calcVisitor.visitCompoundToken(exp));
    }
}
