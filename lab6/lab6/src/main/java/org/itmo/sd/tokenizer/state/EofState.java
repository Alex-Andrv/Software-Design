package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

public class EofState extends State {
    EofState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        // skipp
    }
}
