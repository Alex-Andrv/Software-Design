package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

import static org.itmo.sd.tokenizer.token.binary.BinOp.SUB_OP;

public class SubOpState extends State {
    SubOpState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        if (ch == '-') {
            tokenizer.addToken(SUB_OP);
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
