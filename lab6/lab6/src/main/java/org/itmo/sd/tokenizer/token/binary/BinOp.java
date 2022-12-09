package org.itmo.sd.tokenizer.token.binary;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.primitives.Digit;
import org.itmo.sd.visitor.Visitor;

import java.util.function.BiFunction;

public enum BinOp implements Token {

    DIV_OP(2, "/", (Digit a, Digit b) -> new Digit(a.value() / b.value())),
    MUL_OP(2, "*", (Digit a, Digit b) -> new Digit(a.value() * b.value())),
    SUB_OP(1, "-", (Digit a, Digit b) -> new Digit(a.value() - b.value())),
    SUM_OP(1, "+", (Digit a, Digit b) -> new Digit(a.value() + b.value()));

    private final int priority;
    private final String op;
    private final BiFunction<Digit, Digit, Digit> eval;

    BinOp(int priority, String op, BiFunction<Digit, Digit, Digit> eval) {
        this.priority = priority;
        this.op = op;
        this.eval = eval;
    }

    public void accept(Visitor<?> visitor) {
        visitor.visitBinOp(this);
    }

    public int getPriority() {
        return priority;
    }

    public String getOp() {
        return op;
    }

    public Digit eval(Digit a, Digit b) {
        return eval.apply(a, b);
    }
}
