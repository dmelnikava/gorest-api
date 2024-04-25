package in.co.gorest;

import in.co.gorest.api.rest.RequestTypes;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;

import static io.restassured.RestAssured.given;

public class BaseGorestApiTest extends AbstractTest {

    public Response makeRequest(Pair<RequestTypes, String> endpoint) {
        return given()
                .when()
                .request(endpoint.getKey().getName(), endpoint.getValue())
                .then()
                .log().all()
                .extract()
                .response();
    }

    public JsonPath getResponseAsJsonPath(Response response) {
        return response
                .body()
                .jsonPath();
    }
}
