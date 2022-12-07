package org.itmo.sd.visitor;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.binary.BinOp;
import org.itmo.sd.tokenizer.token.bracket.Bracket;
import org.itmo.sd.tokenizer.token.primitives.Digit;

import java.util.List;

public abstract class Visitor<T> {
    public abstract void visitBracket(Bracket bracket);
    public abstract void visitBinOp(BinOp binOp);
    public abstract void visitDigit(Digit digit);
    public abstract T getResult();

    public T visitCompoundToken(List<Token> tokens) {
        for (var token : tokens) {
            token.accept(this);
        }
        return getResult();
    }
}
