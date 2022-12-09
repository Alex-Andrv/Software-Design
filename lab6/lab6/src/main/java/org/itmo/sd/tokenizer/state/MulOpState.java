package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

import static org.itmo.sd.tokenizer.token.binary.BinOp.MUL_OP;

public class MulOpState extends State {
    MulOpState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        if (ch == '*') {
            tokenizer.addToken(MUL_OP);
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
