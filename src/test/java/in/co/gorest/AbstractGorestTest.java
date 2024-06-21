package in.co.gorest;

import in.co.gorest.api.rest.RequestType;
import in.co.gorest.constans.IConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;

import static io.restassured.RestAssured.given;

public abstract class AbstractGorestTest implements IConstants {

    public Response makeRequest(Pair<RequestType, String> endpoint) {
        return given()
                .filter(new AllureRestAssured())
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
