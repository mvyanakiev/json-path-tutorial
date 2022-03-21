package app.extractors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.List;

public class NodeExtractor {
    private static final String FILE_PATH = "src/main/resources/";

    final static Logger log = LoggerFactory.getLogger(NodeExtractor.class);

    private ObjectMapper mapper;
    private JsonNode node;

    public NodeExtractor() {
        this.mapper = new ObjectMapper();
        try {
            this.node = mapper.readTree(Paths.get(FILE_PATH + "dataSource.json").toFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String extractNameFromNode() {
        final String jsonpathCreatorNamePath = "$.tool.jsonpath.creator.name";

        String name = JsonPath.read(node.toString(), jsonpathCreatorNamePath);
        log.info("Name={}", name);
        return name;
    }

    public List<String> extractLocationsFromNode() {
        final String jsonpathCreatorLocationPath = "$.tool.jsonpath.creator.location.*";

        List<String> list = JsonPath.read(node.toString(), jsonpathCreatorLocationPath);
        log.info("size={}", list.size());
        return list;
    }

    public String changeValue(String newCreatorName) {
        ((ObjectNode) node.get("tool").get("jsonpath").get("creator")).put("name", newCreatorName);

        final String jsonpathCreatorNamePath = "$.tool.jsonpath.creator.name";

        String name = JsonPath.read(node.toString(), jsonpathCreatorNamePath);
        log.info("Changed name={}", name);
        return name;
    }
}
