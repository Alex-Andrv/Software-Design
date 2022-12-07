package org.itmo.sd.tokenizer;

import org.itmo.sd.tokenizer.state.RouterState;
import org.itmo.sd.tokenizer.state.State;
import org.itmo.sd.tokenizer.token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private final Reader reader;

    private State tokenState;

    private List<Token> tokens;

    public Tokenizer(InputStream inputStream) {
        this.reader = new InputStreamReader(inputStream);
        this.tokens = new ArrayList<>();
    }

    // friendly method
    public void setState(State tokenState) {
        this.tokenState = tokenState;
    }

    // friendly method
    public void parse(int currChar) {
       tokenState.parse(currChar);
    }

    public List<Token> getTokenList() throws IOException {
        setState(new RouterState(this));
        int currChar = reader.read();
        while (currChar != -1) {
            parse(currChar);
            currChar = reader.read();
        }
        parse(currChar);
        return tokens;
    }

    // friendly method
    public void addToken(Token token) {
        tokens.add(token);
    }
}
