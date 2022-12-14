package org.itmo.sd.visitor;

import org.itmo.sd.tokenizer.token.binary.BinOp;
import org.itmo.sd.tokenizer.token.bracket.BracketEnum;
import org.itmo.sd.tokenizer.token.primitives.Digit;

import java.util.Stack;

public class CalcVisitor extends Visitor<Integer> {

    private final Stack<Digit> stackDigit;

    public CalcVisitor() {
        this.stackDigit = new Stack<>();
    }

    @Override
    public void visitBracket(BracketEnum bracket) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visitBinOp(BinOp binOp) {
        if (stackDigit.size() < 2) {
            throw new RuntimeException("Wrong input string format");
        }
        Digit a = stackDigit.pop();
        Digit b = stackDigit.pop();
        stackDigit.push(binOp.eval(b, a));
    }

    @Override
    public void visitDigit(Digit digit) {
        stackDigit.push(digit);
    }

    @Override
    public Integer getResult() {
        if (stackDigit.size() != 1) {
            throw new RuntimeException("Wrong input string format");
        }
        return stackDigit.pop().value();
    }
}
