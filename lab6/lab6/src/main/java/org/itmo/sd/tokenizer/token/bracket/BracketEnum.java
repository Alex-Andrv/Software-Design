package org.itmo.sd.tokenizer.token.bracket;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.visitor.Visitor;

public enum BracketEnum implements Token {

    OPEN_BRACKET, CLOSE_BRACKET;

    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visitBracket(this);
    }
}
