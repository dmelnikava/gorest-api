package in.co.gorest.api.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

public class GraphqlTemplate {

    public static String parseGraphql(File file, Object variables) throws IOException {
        String graphqlFileContent = convertInputStreamToString(new FileInputStream(file));
        return convertToGraphqlString(graphqlFileContent, variables);
    }

    private static String convertToGraphqlString(String graphql, Object variables) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        JsonNode nodeVariables = objectMapper.convertValue(variables, JsonNode.class);
        objectNode.put("query", graphql);
        objectNode.set("variables", nodeVariables);
        return objectMapper.writeValueAsString(objectNode);
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
