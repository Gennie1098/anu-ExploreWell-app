package com.anu.gp24s1;

import com.anu.gp24s1.utils.PostTokenizer;
import com.anu.gp24s1.utils.SearchParser;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class SearchParserTest {

    @Test
    public void testBasicTitle() {
        String content = "MyTitle";

        PostTokenizer tokenizer = new PostTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals(parser.getTitle(), "MyTitle");
    }

    @Test
    public void testQuotationTitles() {
        String content = "\"MyTitle\"";

        PostTokenizer tokenizer = new PostTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals(parser.getTitle(), "MyTitle");

        content = "\"MyTitle Is This\"";

        tokenizer = new PostTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals(parser.getTitle(), "MyTitle Is This");
    }

    @Test
    public void testTagsAndLocationsOnly() {
        // tag first
        String content = "#camping @act #climbing @nsw";

        PostTokenizer tokenizer = new PostTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals(parser.getTitle(), "");
        assertEquals(parser.getTags(), Set.of("camping", "climbing"));
        assertEquals(parser.getLocations(), Set.of("act", "nsw"));

        // location first
        content = "@tas #skiing @vic @sa #hiking";

        tokenizer = new PostTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals(parser.getTitle(), "");
        assertEquals(parser.getTags(), Set.of("skiing", "hiking"));
        assertEquals(parser.getLocations(), Set.of("tas", "vic", "sa"));
    }

    @Test
    public void testComplexValid() {
        // Normal title + tags and locations
        String content = "Coree #camping @act #climbing @nsw";

        PostTokenizer tokenizer = new PostTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals(parser.getTitle(), "Coree");
        assertEquals(parser.getTags(), Set.of("camping", "climbing"));
        assertEquals(parser.getLocations(), Set.of("act", "nsw"));

        // Complex title + tags and locations
        content = "\"Mt Amos\" @tas #skiing @vic @sa #hiking";

        tokenizer = new PostTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals(parser.getTitle(), "Mt Amos");
        assertEquals(parser.getTags(), Set.of("skiing", "hiking"));
        assertEquals(parser.getLocations(), Set.of("tas", "vic", "sa"));
    }

    @Test
    public void testComplexInvalid() {
        // invalid complex title
        String content = "\"Mt. Amos\" #camping @act #climbing @nsw";

        PostTokenizer tokenizer = new PostTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());

        // valid title, invalid second location
        content = "\"Lake Ginninderra\" @tas #skiing @#vic @sa #hiking";

        tokenizer = new PostTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());

        // valid complex title, valid tags, invalid unannoted tag, valid tags
        content = "\"Lake Burley Griffin\" @tas #skiing lake @#vic @sa #hiking";

        tokenizer = new PostTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());
    }

    @Test
    public void testEmpty() {
        String content = "";

        PostTokenizer tokenizer = new PostTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());
    }

    @Test
    public void testBadWhitespace() {
        String content = "  MyTitle";

        PostTokenizer tokenizer = new PostTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());

        content = "MyTitle ";

        tokenizer = new PostTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());

        content = "MyTitle #camping @ACT   ";

        tokenizer = new PostTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());
    }


}
