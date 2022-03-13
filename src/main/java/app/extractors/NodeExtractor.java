package app.extractors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.List;

public class NodeExtractor {
    private static final String FILE_PATH = "src/main/resources/";

    final static Logger log = LoggerFactory.getLogger(StringExtractor.class);

    final String jsonpathCreatorNamePath = "$.tool.jsonpath.creator.name";
    final String jsonpathCreatorLocationPath = "$.tool.jsonpath.creator.location.*";

    ObjectMapper mapper = new ObjectMapper();
    JsonNode node;

    public NodeExtractor() {
        try {
            this.node = mapper.readTree(Paths.get(FILE_PATH + "dataSource.json").toFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String extractNameFromNode() {
        String name = JsonPath.read(node.toString(), jsonpathCreatorNamePath);
        log.info("Name={}", name);
        return name;
    }

    public List<String> extractLocationFromNode() {
        List<String> list = JsonPath.read(node.toString(), jsonpathCreatorLocationPath);
        log.info("size={}", list.size());
        return list;
    }


}
