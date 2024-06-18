package in.co.gorest.api.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class QueryBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static String getGraphqlPayload(String filePath, Object variables) {
        File file = new File(filePath);
        try {
            return GraphqlTemplate.parseGraphql(file, variables);
        } catch (IOException e) {
            LOGGER.error("Unable parse object to string!");
            return "";
        }
    }
}
