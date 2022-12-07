package org.itmo.sd.tokenizer.token.binary;

import org.itmo.sd.tokenizer.token.primitives.Digit;

public class MulOp extends BinOp {
    public MulOp() {
        super(2);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MulOp;
    }

    @Override
    public Digit eval(Digit a, Digit b) {
        return new Digit(a.getValue() * b.getValue());
    }

    @Override
    public String getOp() {
        return "*";
    }
}
