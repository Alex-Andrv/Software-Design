package org.itmo.sd;

import org.itmo.sd.tokenizer.Tokenizer;
import org.itmo.sd.tokenizer.token.Token;
import org.itmo.sd.visitor.CalcVisitor;
import org.itmo.sd.visitor.ParserVisitor;
import org.itmo.sd.visitor.PrintVisitor;

import java.io.*;
import java.util.List;

public class App 
{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Should pass input file");
            return;
        }
        String file = args[0];
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String line = bf.readLine();
            Tokenizer tokenizer = new Tokenizer(new ByteArrayInputStream(line.getBytes()));

            ParserVisitor parserVisitor = new ParserVisitor();
            List<Token> polishTokens = parserVisitor.visitCompoundToken(tokenizer.getTokenList());

            PrintVisitor printVisitor = new PrintVisitor();

            System.out.println("Polish entry: " + printVisitor.visitCompoundToken(polishTokens));

            CalcVisitor calcVisitor = new CalcVisitor();

            System.out.println("Result evaluation: " + calcVisitor.visitCompoundToken(polishTokens));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
