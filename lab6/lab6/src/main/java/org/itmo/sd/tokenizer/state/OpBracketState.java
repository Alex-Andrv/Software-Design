package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

import static org.itmo.sd.tokenizer.token.bracket.BracketEnum.OPEN_BRACKET;

public class OpBracketState extends State {
    OpBracketState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        if (ch == '(') {
            tokenizer.addToken(OPEN_BRACKET);
        } else {
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
