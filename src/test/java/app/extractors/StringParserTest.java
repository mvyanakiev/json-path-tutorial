package app.extractors;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringParserTest {

    private final StringExtractor parser = new StringExtractor();

    @Test
    void getCreator() {
        assertEquals("Jayway Inc.", parser.extractCreatorName());
    }

    @Test
    void getLocations() {
        List<String> jsonpathCreatorLocation = parser.extractLocation();
        assertThat(jsonpathCreatorLocation.toString(), containsString("Malmo"));
        assertThat(jsonpathCreatorLocation.toString(), containsString("San Francisco"));
        assertThat(jsonpathCreatorLocation.toString(), containsString("Helsingborg"));
    }

    @Test
    void getCreatorByNode() throws JsonProcessingException {
        assert("Jayway Inc.").equals(parser.byNode());
    }
}