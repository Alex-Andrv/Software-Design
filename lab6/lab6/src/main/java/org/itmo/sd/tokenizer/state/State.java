package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

public abstract class State {
    Tokenizer tokenizer;

    State(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public abstract void parse(int ch);
}
