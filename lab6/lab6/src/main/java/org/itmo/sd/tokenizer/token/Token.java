package org.itmo.sd.tokenizer.token;

import org.itmo.sd.visitor.Visitor;

public interface Token {
    public void accept(Visitor<?> visitor);
}
