package org.itmo.sd.tokenizer.state;

import org.itmo.sd.tokenizer.Tokenizer;

public class RouterState extends State {

    public RouterState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parse(int ch) {
        switch (ch) {
            case '(':
                tokenizer.setState(new OpBracketState(tokenizer));
                break;
            case ')':
                tokenizer.setState(new ClBracketState(tokenizer));
                break;
            case '+':
                tokenizer.setState(new SumOpState(tokenizer));
                break;
            case '-':
                tokenizer.setState(new SubOpState(tokenizer));
                break;
            case '*':
                tokenizer.setState(new MulOpState(tokenizer));
                break;
            case '/':
                tokenizer.setState(new DivOpState(tokenizer));
                break;
            case ' ':
                tokenizer.setState(new SpaceState(tokenizer));
                break;
            case -1:
                tokenizer.setState(new EofState(tokenizer));
                break;
            default:
                if (Character.isDigit(ch)) {
                    tokenizer.setState(new DigitState(tokenizer));
                } else {
                    tokenizer.setState(new ErrorState(tokenizer));
                    return;
                }
        }
        tokenizer.parse(ch);
    }
}
