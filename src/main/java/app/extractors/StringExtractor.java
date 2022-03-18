package app.extractors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static app.constants.Contents.jsonDataSourceString;

public class StringExtractor {
    final static Logger log = LoggerFactory.getLogger(StringExtractor.class);

    final String jsonpathCreatorLocationPath = "$['tool']['jsonpath']['creator']['location'][*]";
    final String jsonpathCreatorNamePath = "$['tool']['jsonpath']['creator']['name']";
    final DocumentContext jsonContext = JsonPath.parse(jsonDataSourceString);

    public String extractCreatorName() {
        String jsonpathCreatorName = jsonContext.read(jsonpathCreatorNamePath);
        log.info("Extracted creator: {}", jsonpathCreatorName);
        return jsonpathCreatorName;
    }

    public List<String> extractLocations() {
        List<String> jsonpathCreatorLocation = jsonContext.read(jsonpathCreatorLocationPath);
        log.info("Extracted {} locations", jsonpathCreatorLocation.size());
        return jsonpathCreatorLocation;
    }

    public String byNode() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree(jsonDataSourceString);
        String name = node.get("tool").get("jsonpath").get("creator").get("name").asText();
        log.info("Creator name is: {}", name);
        return name;
    }

    public String changeValues() {
        String changedValue = jsonDataSourceString.replace("Jayway Inc.", "New Creator Name");
        DocumentContext changedJsonContext = JsonPath.parse(changedValue);
        String jsonpathCreatorName = changedJsonContext.read(jsonpathCreatorNamePath);
        log.info("Extracted creator: {}", jsonpathCreatorName);
        return jsonpathCreatorName;
    }
}
