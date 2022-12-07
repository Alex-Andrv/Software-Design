package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;
import org.itmo.sd.tokenizer.token.bracket.ClBracket;

public class ClBracketState extends State {
    ClBracketState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        if (ch == ')') {
            tokenizer.addToken(new ClBracket());
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
