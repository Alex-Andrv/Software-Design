package org.itmo.sd.tokenizer.token;

import org.itmo.sd.visitor.Visitor;

public interface Token {
    void accept(Visitor<?> visitor);
}
