package org.itmo.sd.tokenizer.token.bracket;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.visitor.Visitor;

public abstract class Bracket implements Token  {

    public void accept(Visitor visitor) {
        visitor.visitBracket(this);
    }

}
