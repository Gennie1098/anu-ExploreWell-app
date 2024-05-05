package com.anu.gp24s1.utils;

import java.util.ArrayList;
import java.util.List;

/* The Grammar for posts is:

<X> ::= alpha <A> | nonalpha <B> | at <C> | hashtag <C>
<A> ::= nonalpha | nonalpha <B> | at <C> | hashtag <C>
<B> ::= alpha | alpha <A> | at <C> | hashtag <C>
<C> ::= alpha | alpha <A>

For example:
<X>
alpha <A>
alpha nonalpha <B>
alpha nonalpha alpha <A>
alpha nonalpha alpha nonalpha <B>
alpha nonalpha alpha nonalpha at <C>
alpha nonalpha alpha nonalpha at alpha <A>
alpha nonalpha alpha nonalpha at alpha nonalpha

Would be the tokenization of the string:
"Come visit @ACT!"

"Alpha", "NonAlpha", "At", and "Hashtag" are Tokens
 */

public class PostParser {

    public List<Token> parseContent(CharSequence input) {

        int idx = 0;

        List<Token> tokens = new ArrayList<Token>();

        while (idx < input.length()) {
            // check if this is a
        }

        return null;
    }

    public Token parseTag(CharSequence input) {


        return null;
    }

    public Token parseLocation(CharSequence input) {

        return null;
    }

}


