package in.co.gorest.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class CustomAssert {

    public static void assertJsonSchemaIsValid(Response response, String schemaPath) {
        JsonSchemaValidator validator = JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath);
        response.then()
                .assertThat()
                .body(validator);
    }
}
