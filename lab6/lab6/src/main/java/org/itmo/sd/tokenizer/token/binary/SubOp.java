package org.itmo.sd.tokenizer.token.binary;

import org.itmo.sd.tokenizer.token.primitives.Digit;

public class SubOp extends BinOp {
    public SubOp() {
        super(1);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SubOp;
    }

    @Override
    public Digit eval(Digit a, Digit b) {
        return new Digit(a.getValue() - b.getValue());
    }

    @Override
    public String getOp() {
        return "-";
    }
}
