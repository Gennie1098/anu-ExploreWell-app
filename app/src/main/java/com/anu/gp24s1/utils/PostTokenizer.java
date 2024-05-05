package com.anu.gp24s1.utils;

import java.util.ArrayList;
import java.util.List;

/** Implements a tokenizer which extracts tokens
 * pertaining to Posts, distinguishing between:
 * - '@'
 * - '#'
 * - alphanumeric
 * - non-alphanumeric
 * tokens.
 *
 * @author  u7284324    Lachlan Stewart
 * */
public class PostTokenizer {

    private final CharSequence content;
    private int idx;
    public Token curToken;

    public PostTokenizer(CharSequence content) {
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
        if (idx >= content.length()) {
            return null;
        }

        char curChar = content.charAt(idx);

        if (curChar == '@') {
            // 'at' symbol
            idx++;
            return new Token(idx - 1, idx, "@", TokenType.At);
        } else if (curChar == '#') {
            // 'hashtag' symbol
            idx++;
            return new Token(idx - 1, idx, "#", TokenType.Hashtag);
        } else if (Character.isLetterOrDigit(curChar)) {
            // alphanumeric
            int start = idx;
            while (idx < content.length() && Character.isLetterOrDigit(content.charAt(idx))) {
                idx++;
            }
            if (idx > start) {
                return new Token(start, idx, content.subSequence(start, idx), TokenType.Alpha);
            }
        } else {
            // nonalphanumeric (whitespace and punctuation etc.)
            int start = idx;
            while (idx < content.length()
                    && !Character.isLetterOrDigit(content.charAt(idx))
                    && content.charAt(idx) != '@'
                    && content.charAt(idx) != '#') {
                idx++;
            }
            if (idx > start) {
                return new Token(start, idx, content.subSequence(start, idx), TokenType.Alpha);
            }
        }

        return null;
    }

}
