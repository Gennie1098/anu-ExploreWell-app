package com.anu.gp24s1;

import com.anu.gp24s1.utils.SearchTokenizer;
import com.anu.gp24s1.utils.SearchParser;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;

public class SearchParserTest {

    @Test
    public void testBasicTitle() {
        String content = "MyTitle";

        SearchTokenizer tokenizer = new SearchTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals("MyTitle", parser.getTitle());
        assertTrue(parser.getTags().isEmpty());
        assertTrue(parser.getLocations().isEmpty());

    }

    @Test
    public void testTagsAndLocationsOnly() {
        // tag first
        String content = "#camping @act #climbing @nsw";

        SearchTokenizer tokenizer = new SearchTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals("", parser.getTitle());
        assertEquals(Set.of("camping", "climbing"), parser.getTags());
        assertEquals(Set.of("act", "nsw"), parser.getLocations());

        // location first
        content = "@tas #skiing @vic @sa #hiking";

        tokenizer = new SearchTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals("", parser.getTitle());
        assertEquals(Set.of("skiing", "hiking"), parser.getTags());
        assertEquals(Set.of("tas", "vic", "sa"), parser.getLocations());
    }

    // TODO: Add a partially correct title test, and check that the returned title is blank

    @Test
    public void testPunctuatedTitle() {
        // This title has some non alphanumeric characters
        String content = "Visit Mt. Majura";

        SearchTokenizer tokenizer = new SearchTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals("Visit Mt. Majura", parser.getTitle());
    }


    @Test
    public void testComplexValid() {
        // Complex title + tags and locations
        String content = "Cotter Dam #camping @act #climbing @nsw";

        SearchTokenizer tokenizer = new SearchTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals("Cotter Dam", parser.getTitle());
        assertEquals(Set.of("camping", "climbing"), parser.getTags());
        assertEquals(Set.of("act", "nsw"), parser.getLocations());

        // Complex title + tags and locations
        content = "Mt Amos Tasmania @tas #skiing @vic @sa #hiking";

        tokenizer = new SearchTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals( "Mt Amos Tasmania", parser.getTitle());
        assertEquals(Set.of("skiing", "hiking"), parser.getTags());
        assertEquals(Set.of("tas", "vic", "sa"), parser.getLocations());
    }

    @Test
    public void testComplexInvalid() {

        // valid title, bad formatting of locations and tags
        String content = "Lake Ginninderra @tas #skiing @#vic @sa #hiking";

        SearchTokenizer tokenizer = new SearchTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());

        // valid complex title, valid tags, invalid unannotated tag, more valid tags
        content = "Lake Burley Griffin @tas #skiing lake @#vic @sa #hiking";

        tokenizer = new SearchTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());
    }

    @Test
    public void testEmpty() {
        String content = "";

        SearchTokenizer tokenizer = new SearchTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertFalse(parser.parseX());
    }

    @Test
    public void testWhitespace() {
        String content = "  MyTitle";

        SearchTokenizer tokenizer = new SearchTokenizer(content);
        SearchParser parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals("MyTitle", parser.getTitle());

        content = "MyTitle #camping     @ACT   ";

        tokenizer = new SearchTokenizer(content);
        parser = new SearchParser(tokenizer);

        assertTrue(parser.parseX());
        assertEquals("MyTitle", parser.getTitle());
        assertEquals(Set.of("camping"), parser.getTags());
        assertEquals(Set.of("ACT"), parser.getLocations());
    }


}
