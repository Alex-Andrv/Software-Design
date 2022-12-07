package org.itmo.sd.tokenizer.token.binary;

import org.itmo.sd.tokenizer.token.primitives.Digit;

public class DivOp extends BinOp {
    public DivOp() {
        super(2);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DivOp;
    }

    @Override
    public Digit eval(Digit a, Digit b) {
        return new Digit(a.getValue() / b.getValue());
    }

    @Override
    public String getOp() {
        return "/";
    }
}
