package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

public class SpaceState extends State {

    SpaceState(Tokenizer tokenizer) {
        super(tokenizer);
    }
    @Override
    public void parse(int ch) {
        if (ch == ' ') {
            // skipp
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
