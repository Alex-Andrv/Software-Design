package org.itmo.sd.tokenizer.token.primitives;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.visitor.Visitor;

public class Digit implements Token {

    private final int value;

    public Digit(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Digit digit)) {
            return false;
        }
        return digit.value == value;
    }

    public void accept(Visitor visitor) {
        visitor.visitDigit(this);
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
