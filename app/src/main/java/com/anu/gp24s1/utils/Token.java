package com.anu.gp24s1.utils;

public class Token {

    private final int start, end;
    private final CharSequence content;

    private final TokenType tokenType;

    public Token(int start, int end, CharSequence content, TokenType tokenType) {
        this.start = start;
        this.end = end;
        this.content = content;
        this.tokenType = tokenType;
    }


    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public TokenType getType() {
        return tokenType;
    }

    public CharSequence getContent() {
        return content;
    }
}
