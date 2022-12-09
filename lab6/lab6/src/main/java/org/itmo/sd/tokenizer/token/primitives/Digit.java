package org.itmo.sd.tokenizer.token.primitives;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.visitor.Visitor;

public record Digit(int value) implements Token {

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Digit digit)) {
            return false;
        }
        return digit.value == value;
    }

    public void accept(Visitor<?> visitor) {
        visitor.visitDigit(this);
    }

    public String toString() {
        return String.valueOf(value);
    }
}
