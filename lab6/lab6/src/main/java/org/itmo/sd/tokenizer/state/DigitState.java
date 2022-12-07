package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;
import org.itmo.sd.tokenizer.token.primitives.Digit;

public class DigitState extends State {

    private StringBuilder digit;

    DigitState(Tokenizer tokenizer) {
        super(tokenizer);
        digit = new StringBuilder();
    }

    @Override
    public void parse(int ch) {
        if (Character.isDigit(ch)) {
            digit.append((char) ch);
        } else {
            int value = Integer.parseInt(digit.toString());
            tokenizer.addToken(new Digit(value));
            tokenizer.setState(new RouterState(tokenizer));
            tokenizer.parse(ch);
        }
    }
}
