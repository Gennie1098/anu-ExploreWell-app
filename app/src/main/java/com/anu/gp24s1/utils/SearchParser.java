package com.anu.gp24s1.utils;

import java.util.HashSet;
import java.util.Set;

/** Implements the parser for strings given as input to the search bar
 *  Formal Grammar for searches:
 *     <X> ::= <T> | <T> <Y> | <Y>
 *     <T> ::= alpha | alpha <T>
 *     <Y> ::= <Z> | <Z> <Y>
 *     <Z> ::= hashtag alpha | at alpha
 *
 *
 * Calling parseX() will return true if the content tokenized by the
 * provided tokenizer is valid. As the content is parsed, fields are updated
 * in this object which contain the information that the user has entered
 *
 * The grammar represents an optional title, not containing '#' or '@',
 * optionally followed by a list of @location or #tag strings separated
 * by whitespaces
 *
 * @author u7284324 Lachlan Stewart
 * */
public class SearchParser {

    private Set<String> tags = null;
    private Set<String> locations = null;
    private String title = "";
    private SearchTokenizer tokenizer;

    public SearchParser(SearchTokenizer tokenizer) {
        this.tokenizer = tokenizer;
        tags = new HashSet<String>();
        locations = new HashSet<String>();
    }

    /** Parse the <X> expression:
     * <X> ::= <T> | <T> <Y> | <Y>
     *
     * @return  boolean     True if the expression was parsed, making it valid
     * @author  u7284324    Lachlan Stewart
     * */
    public boolean parseX() {

        Token curToken = tokenizer.curToken();
        if (curToken == null) { return false; };

        boolean parsedT = parseT();

        if (parsedT) {
            curToken = tokenizer.curToken();
            if (curToken == null) {
                return true;
            } else {
                return parseY();
            }
        } else {
            title = "";
            return parseY();
        }

    }

    /** Parse the <T> expression:
     * <T> ::= alpha | alpha <T>
     *
     * Note: this can be used to check that the user is providing a valid
     * title, hence it is made public
     *
     * @return  boolean     True if the expression was parsed, making it valid
     * @author  u7284324    Lachlan Stewart
     * */
    public boolean parseT() {

        Token curToken = tokenizer.curToken();
        if (curToken == null) {
            return false;
        }

        boolean parsedT = false;

        StringBuilder titleStringBuilder = new StringBuilder();
        if (curToken.getType() == TokenType.Alpha) {
            titleStringBuilder.append(curToken.getContent());
            parsedT = true;

            tokenizer.next();
            curToken = tokenizer.curToken();
        }
        while (curToken != null && curToken.getType() == TokenType.Alpha) {
            titleStringBuilder.append(" ");
            titleStringBuilder.append(curToken.getContent().toString());

            tokenizer.next();
            curToken = tokenizer.curToken();
        }

        title = titleStringBuilder.toString();

        return parsedT;
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

        Token curToken = tokenizer.curToken();
        if (curToken == null) {
            return true;
        } else {
            return parseY();
        }
    }

    /** Parse the <Z> expression:
     * <Z> ::= hashtag alpha | at alpha
     *
     * @return  boolean     True if the expression was parsed, making it valid
     * @author  u7284324    Lachlan Stewart
     * */
    private boolean parseZ() {

        Token curToken = tokenizer.curToken();
        if (curToken == null) { return false; };

        TokenType type = curToken.getType();
        if (type == TokenType.Alpha) { return false; }

        tokenizer.next();
        curToken = tokenizer.curToken();
        if (curToken == null) {
            return false;
        } else if (curToken.getType() != TokenType.Alpha) {
            return false;
        }

        if (type == TokenType.At) {
            locations.add(curToken.getContent().toString());
        } else if (type == TokenType.Hashtag) {
            tags.add(curToken.getContent().toString());
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
