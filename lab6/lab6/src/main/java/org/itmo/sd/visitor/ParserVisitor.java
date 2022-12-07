package org.itmo.sd.visitor;

import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.tokenizer.token.binary.BinOp;
import org.itmo.sd.tokenizer.token.bracket.Bracket;
import org.itmo.sd.tokenizer.token.bracket.ClBracket;
import org.itmo.sd.tokenizer.token.bracket.OpBracket;
import org.itmo.sd.tokenizer.token.primitives.Digit;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor extends Visitor<List<Token>> {

    private final List<Token> polishTokens;
    private final Stack<Token> stackOperation;

    public ParserVisitor() {
        polishTokens = new ArrayList<>();
        stackOperation = new Stack<>();
    }

    @Override
    public void visitBracket(Bracket bracket) {
        if (bracket instanceof OpBracket) {
            stackOperation.add(bracket);
        } else {
            while (!stackOperation.isEmpty() && !(stackOperation.peek() instanceof OpBracket)) {
                if (stackOperation.peek() instanceof ClBracket) {
                    throw new RuntimeException("Wrong input string format");
                }
                polishTokens.add(stackOperation.pop());
            }
            if (stackOperation.isEmpty()) {
                throw new RuntimeException("Wrong input string format");
            }
            stackOperation.pop();
        }
    }

    @Override
    public void visitBinOp(BinOp binOp) {
        while (canPopLastOp(binOp)) {
            polishTokens.add(stackOperation.pop());
        }
        stackOperation.push(binOp);
    }

    private boolean canPopLastOp(BinOp binOp) {
        if (!stackOperation.isEmpty()) {
            Token peek = stackOperation.peek();
            if (peek instanceof BinOp predBinOp) {
                return predBinOp.getPriority() >= binOp.getPriority();
            }
        }
        return false;
    }

    @Override
    public void visitDigit(Digit digit) {
        polishTokens.add(digit);
    }

    @Override
    public List<Token> getResult() {
        while (!stackOperation.isEmpty()) {
            polishTokens.add(stackOperation.pop());
        }
        return polishTokens;
    }
}
