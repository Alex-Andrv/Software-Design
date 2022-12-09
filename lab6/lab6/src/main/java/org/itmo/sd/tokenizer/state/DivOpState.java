package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

import static org.itmo.sd.tokenizer.token.binary.BinOp.DIV_OP;

public class DivOpState extends State {
    DivOpState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        if (ch == '/') {
            tokenizer.addToken(DIV_OP);
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
