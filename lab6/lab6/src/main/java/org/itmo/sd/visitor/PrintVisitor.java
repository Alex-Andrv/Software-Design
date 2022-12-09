package org.itmo.sd.visitor;

import org.itmo.sd.tokenizer.token.binary.BinOp;
import org.itmo.sd.tokenizer.token.bracket.BracketEnum;
import org.itmo.sd.tokenizer.token.primitives.Digit;

import java.util.StringJoiner;

public class PrintVisitor extends Visitor<String> {

    private final StringJoiner sj;

    public PrintVisitor() {
        this.sj = new StringJoiner(" ");
    }

    @Override
    public void visitBracket(BracketEnum bracket) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visitBinOp(BinOp binOp) {
        sj.add(binOp.getOp());
    }

    @Override
    public void visitDigit(Digit digit) {
        sj.add(digit.toString());
    }

    @Override
    public String getResult() {
        return sj.toString();
    }
}
