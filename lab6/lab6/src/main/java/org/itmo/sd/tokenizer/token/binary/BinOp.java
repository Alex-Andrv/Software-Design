package org.itmo.sd.tokenizer.token.binary;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.itmo.sd.visitor.Visitor;

public abstract class BinOp implements Token {

    private final int priority;

    protected BinOp(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public abstract Digit eval(Digit a, Digit b);

    public abstract String getOp();

    public void accept(Visitor visitor) {
        visitor.visitBinOp(this);
    }
}
