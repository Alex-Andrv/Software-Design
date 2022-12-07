package org.itmo.sd.tokenizer;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.binary.DivOp;
import org.itmo.sd.tokenizer.token.binary.MulOp;
import org.itmo.sd.tokenizer.token.binary.SubOp;
import org.itmo.sd.tokenizer.token.binary.SumOp;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.itmo.sd.visitor.PrintVisitor;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrintVisitorTest {
    @Test
    public void simpleTest() {
        List<Token> exp =
                List.of(
                        new Digit(1),
                        new Digit(2),
                        new SumOp(),
                        new Digit(3),
                        new DivOp());

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


        PrintVisitor printVisitor = new PrintVisitor();

        assertEquals("1 2 10 * + 10 - 17 7 * 8 / + 3 / 1 2 + *", printVisitor.visitCompoundToken(exp));
    }
}
