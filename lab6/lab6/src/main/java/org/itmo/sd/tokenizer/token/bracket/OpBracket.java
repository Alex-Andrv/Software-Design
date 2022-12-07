package org.itmo.sd.tokenizer.token.bracket;

public class OpBracket extends Bracket {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof OpBracket;
    }
}
