package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

import static org.itmo.sd.tokenizer.token.binary.BinOp.SUM_OP;

public class SumOpState extends State {
    SumOpState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        if (ch == '+') {
            tokenizer.addToken(SUM_OP);
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
