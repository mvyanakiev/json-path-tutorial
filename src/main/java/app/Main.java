package app;

import app.extractors.NodeExtractor;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {
    public static void main(String[] args) {
        NodeExtractor nodeExtractor = new NodeExtractor();
        System.out.println(nodeExtractor.extractNameFromNode());
    }
}
