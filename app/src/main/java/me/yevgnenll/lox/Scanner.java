package me.yevgnenll.lox;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    public Scanner(String source) {
        this.source = source;
    }

    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }
        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(' : addToken(TokenType.LEFT_PAREN); break;
            case ')' : addToken(TokenType.RIGHT_PAREN); break;
            case '{' : addToken(TokenType.LEFT_BRACE); break;
            case '}' : addToken(TokenType.RIGHT_BRACE); break;
            case ',' : addToken(TokenType.COMMA); break;
            case '.' : addToken(TokenType.DOT); break;
            case '-' : addToken(TokenType.MINUS); break;
            case '+' : addToken(TokenType.PLUS); break;
            case ';' : addToken(TokenType.SEMICOLON); break;
            case '*' : addToken(TokenType.STAR); break;
            case '!' : addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG); break;
            default:
                Lox.error(line, "Unexpected character.");
                break;
        }
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current ++;
        return true;
    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(TokenType tokenType) {
        addToken(tokenType, null);
    }

    private void addToken(TokenType tokenType, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(tokenType, text, literal, line));
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }
}
