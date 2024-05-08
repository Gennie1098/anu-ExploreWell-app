package com.anu.gp24s1.utils;

/** Implements a tokenizer which extracts tokens
 * pertaining to Posts, distinguishing between:
 * - '@'
 * - '#'
 * - alpha (anything contiguous that isn't '@' or '#'
 *
 * @author  u7284324    Lachlan Stewart
 * */
public class SearchTokenizer {

    private final CharSequence content;
    private int idx;
    private Token curToken;

    public SearchTokenizer(CharSequence content) {
        this.content = content;
        curToken = getNext();
    }

    /**
     * @return  boolean True if there are still characters yet to be tokenized,
     *                  meaning that getNext() will not return null
     *
     * @author  u7284324    Lachlan Stewart
     * */
    public boolean hasNext() {
        return idx < content.length();
    }

    /**
     * Advances the current token
     *
     * @author  u7284324    Lachlan Stewart
     * */
    public void next() {
        curToken = getNext();
    }

    /**
     * Tokenizes the next token of the remaining text in content,
     * advancing idx
     *
     * @return  Token    null if no characters remain, otherwise the next Token in content
     *
     * @author  u7284324    Lachlan Stewart
     * */
    private Token getNext() {

        // advance idx past whitespace:
        while (idx < content.length() && content.charAt(idx) == ' ') {
            idx++;
        }

        // check if we are at the end of the string
        if (idx >= content.length()) {
            return null;
        }

        char curChar = content.charAt(idx);

        if (curChar == '@') {
            // 'at' symbol
            idx++;
            return new Token("@", TokenType.At);
        } else if (curChar == '#') {
            // 'hashtag' symbol
            idx++;
            return new Token("#", TokenType.Hashtag);
        } else {
            // other
            int start = idx;
            while (idx < content.length()
                    && content.charAt(idx) != '@'
                    && content.charAt(idx) != '#'
                    && content.charAt(idx) != ' ') {
                idx++;
            }
            return new Token(content.subSequence(start, idx), TokenType.Alpha);
        }
    }

    public Token curToken() {
        return curToken;
    }

}
