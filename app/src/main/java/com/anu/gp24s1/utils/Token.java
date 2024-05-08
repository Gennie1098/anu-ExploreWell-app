package com.anu.gp24s1.utils;

/**
 * Token class for the SearchParser and SearchTokenizer
 *
 * @author  u7284324    Lachlan Stewart
 * */
public class Token {
    private final CharSequence content;

    private final TokenType tokenType;

    public Token(CharSequence content, TokenType tokenType) {
        this.content = content;
        this.tokenType = tokenType;
    }

    public TokenType getType() {
        return tokenType;
    }

    public CharSequence getContent() {
        return content;
    }
}
