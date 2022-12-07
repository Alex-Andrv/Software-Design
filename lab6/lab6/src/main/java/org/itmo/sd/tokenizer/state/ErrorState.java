package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

public class ErrorState extends State {
    ErrorState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        // skipp
    }
}
