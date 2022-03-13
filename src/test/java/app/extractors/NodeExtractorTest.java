package app.extractors;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NodeExtractorTest {

    private final NodeExtractor extractor = new NodeExtractor();

    @Test
    void getCreator() {
        assertEquals("Jayway Inc.", extractor.extractNameFromNode());
    }

    @Test
    void getLocations() {
        List<String> list = extractor.extractLocationsFromNode();
        assertThat(list.toString(), containsString("Malmo"));
        assertThat(list.toString(), containsString("San Francisco"));
        assertThat(list.toString(), containsString("Helsingborg"));
    }
}