package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;
import org.itmo.sd.tokenizer.token.bracket.OpBracket;

public class OpBracketState extends State {
    OpBracketState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        if (ch == '(') {
            tokenizer.addToken(new OpBracket());
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
