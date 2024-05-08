package com.anu.gp24s1.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/** Implements the parser for strings given as input to the search bar
 *  Formal Grammar for searches:
 *     <X> ::= alpha | nonalpha("\"") <T> nonalpha("\") whitespace | alpha whitespace <Y> | <Y>
 *     <T> ::= alpha | alpha whitespace <T>
 *     <Y> ::= <Z> | <Z> whitespace <Y>
 *     <Z> ::= hashtag alpha | at alpha
 *
 *     <X> ::= <T> | <T> <Y> | <Y>
 *     <T> ::= alpha | alpha whitespace <T>
 *     <Y> ::= <Z> | <Z> whitespace <Y>
 *     <Z> ::= hashtag alpha | at alpha
 *
 * Calling parseX() will return true if the content tokenized by the
 * provided tokenizer is valid. As the content is parsed, fields are updated
 * in this object which contain the information that the user has entered
 *
 * The grammar represents an optional alphanumeric title, enclosed by quotation marks when
 * the title contains spaces, optionally followed by a list of @location or #tag strings separated
 * by whitespaces
 *
 * Under no circumstances should the search input start or end with whitespace, so it is
 * recommended to first trim leading and trailing whitespace before instantiating
 * the tokenizer provided to this parser.
 *
 * @author u7284324 Lachlan Stewart
 * */
public class SearchParser {

    private Set<String> tags = null;
    private Set<String> locations = null;
    private String title = "";
    private PostTokenizer tokenizer;

    public SearchParser(PostTokenizer tokenizer) {
        this.tokenizer = tokenizer;
        tags = new HashSet<String>();
        locations = new HashSet<String>();
    }

    /** Parse the <X> expression:
     * <X> ::= alpha | nonalpha("\"") <T> nonalpha("\") whitespace | alpha whitespace <Y> | <Y>
     *
     * @return  boolean     True if the expression was parsed, making it valid
     * @author  u7284324    Lachlan Stewart
     * */
    public boolean parseX() {

        Token curToken = tokenizer.curToken;
        if (curToken == null) { return false; };

        if (curToken.getType() == TokenType.NonAlpha && curToken.getContent().equals("\"")) {
            // title enclosed in quotation marks
            if (tokenizer.hasNext()) {
                tokenizer.next();
            } else {
                return false;
            }
            boolean parsedT = parseT();
            if (!parsedT) {
                return false;
            }
            curToken = tokenizer.curToken;
            if (curToken == null) {
                return false;
            } else if (!(curToken.getType() == TokenType.NonAlpha && curToken.getContent().equals("\""))) {
                return false;
            }
        } else if (curToken.getType() != TokenType.Alpha) {
            // no title, so parse <Y>
            return parseY();
        } else {
            title = curToken.getContent().toString();
        }

        // got title, parse <Y> if it exists.
        if (tokenizer.hasNext()) {
            tokenizer.next();
            curToken = tokenizer.curToken;
            if (curToken.getType() == TokenType.WhiteSpace) {
                tokenizer.next();
                return parseY();
            } else {
                return false;
            }
        }

        return true;
    }

    /** Parse the <T> expression:
     * <T> ::= alpha | alpha whitespace <T>
     *
     * Note: this can be used to check that the user is providing a valid
     * alphanumerical title with single whitespaces inbetween words,
     * hence it is made public
     *
     * @return  boolean     True if the expression was parsed, making it valid
     * @author  u7284324    Lachlan Stewart
     * */
    public boolean parseT() {

        Token curToken = tokenizer.curToken;
        if (curToken == null) {
            return false;
        }

        if (curToken.getType() == TokenType.Alpha) {
            title = title + curToken.getContent().toString();
            if (tokenizer.hasNext()) {
                tokenizer.next();
            } else {
                return false;
            }
            curToken = tokenizer.curToken;

            if (curToken.getType() == TokenType.WhiteSpace) {
                title = title + " ";
                tokenizer.next();
                return parseT();
            }
        } else {
            return false;
        }

        return true;
    }

    /** Parse the <Y> expression:
     * <Y> ::= <Z> | <Z> whitespace <Y>
     *
     * @return  boolean     True if the expression was parsed, making it valid
     * @author  u7284324    Lachlan Stewart
     * */
    private boolean parseY() {

        boolean parsedZ = parseZ();

        if (!parsedZ) { return false; };

        Token curToken = tokenizer.curToken;
        if (curToken == null) {
            return true;
        } else if (curToken.getType() == TokenType.WhiteSpace) {
            if (tokenizer.hasNext()) {
                tokenizer.next();
            } else {
                return false;
            }
            return parseY();
        }

        return false;
    }

    /** Parse the <Z> expression:
     * <Z> ::= hashtag alpha | at alpha
     *
     * @return  boolean     True if the expression was parsed, making it valid
     * @author  u7284324    Lachlan Stewart
     * */
    private boolean parseZ() {

        Token curToken = tokenizer.curToken;
        if (curToken == null) { return false; };

        TokenType type = null;

        if (curToken.getType() == TokenType.At) {
            type = TokenType.At;
            if (tokenizer.hasNext()) {
                tokenizer.next();
            } else {
                return false;
            }
            curToken = tokenizer.curToken;
        } else if (curToken.getType() == TokenType.Hashtag) {
            type = TokenType.Hashtag;
            if (tokenizer.hasNext()) {
                tokenizer.next();
            } else {
                return false;
            }
            curToken = tokenizer.curToken;
        } else {
            return false;
        }

        String content = "";
        if (curToken.getType() == TokenType.Alpha) {
            content = curToken.getContent().toString();
        } else {
            return false;
        }

        if (type == TokenType.At) {
            locations.add(content);
        } else {
            tags.add(content);
        }

        tokenizer.next();

        return true;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTags() {
        return tags;
    }

    public Set<String> getLocations() {
        return locations;
    }
}
