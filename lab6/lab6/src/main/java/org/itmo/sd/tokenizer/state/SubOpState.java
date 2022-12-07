package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;
import org.itmo.sd.tokenizer.token.binary.SubOp;

public class SubOpState extends State {
    SubOpState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        if (ch == '-') {
            tokenizer.addToken(new SubOp());
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
