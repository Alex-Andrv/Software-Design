package org.itmo.sd.tokenizer;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.binary.DivOp;
import org.itmo.sd.tokenizer.token.binary.MulOp;
import org.itmo.sd.tokenizer.token.binary.SubOp;
import org.itmo.sd.tokenizer.token.binary.SumOp;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.itmo.sd.visitor.CalcVisitor;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CalcVisitorTest {
    @Test
    public void simpleTest() {
        List<Token> exp =
                List.of(
                        new Digit(1),
                        new Digit(2),
                        new SumOp(),
                        new Digit(3),
                        new DivOp());

        CalcVisitor calcVisitor = new CalcVisitor();

        for (var token : exp) {
            token.accept(calcVisitor);
        }

        assertEquals(new Integer(1), calcVisitor.getResult());
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


        CalcVisitor calcVisitor = new CalcVisitor();

        for (var token : exp) {
            token.accept(calcVisitor);
        }

        assertEquals(new Integer(24), calcVisitor.getResult());
    }
}
