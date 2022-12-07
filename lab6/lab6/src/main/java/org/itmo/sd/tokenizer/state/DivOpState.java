package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;
import org.itmo.sd.tokenizer.token.binary.DivOp;

public class DivOpState extends State {
    DivOpState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        if (ch == '/') {
            tokenizer.addToken(new DivOp());
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
