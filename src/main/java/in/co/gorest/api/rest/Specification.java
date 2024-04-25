package in.co.gorest.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.co.gorest.constans.IConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Map;

public class Specification implements IConstants {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static RequestSpecification requestSpecification(String url, String body) {
        return getBaseRequestSpec(url)
                .setBody(body)
                .build();
    }

    public static RequestSpecification requestSpecification(String url, Map<String, Integer> queryParameters) {
        return getBaseRequestSpec(url)
                .addQueryParams(queryParameters)
                .build();
    }

    public static RequestSpecification requestSpecification(String url) {
        return getBaseRequestSpec(url)
                .build();
    }

    public static RequestSpecBuilder getBaseRequestSpec(String url) {
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .setBaseUri(url)
                .setContentType(ContentType.JSON);
    }

    public static ResponseSpecification responseSpecification(StatusCodes statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode.getValue())
                .build();
    }

    public static void setOption(String url, Object body, StatusCodes statusCode) {
        RestAssured.reset();
        String bodyAsString = getBodyAsString(body);
        RestAssured.requestSpecification = requestSpecification(url, bodyAsString);
        RestAssured.responseSpecification = responseSpecification(statusCode);
    }

    public static void setOption(String url, Map<String, Integer> queryParameters, StatusCodes statusCode) {
        RestAssured.reset();
        RestAssured.requestSpecification = requestSpecification(url, queryParameters);
        RestAssured.responseSpecification = responseSpecification(statusCode);
    }

    public static void setOption(String url, StatusCodes statusCode) {
        RestAssured.reset();
        RestAssured.requestSpecification = requestSpecification(url);
        RestAssured.responseSpecification = responseSpecification(statusCode);
    }

    public static String getBodyAsString(Object body) {
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyAsString = "";
        try {
            bodyAsString = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable parse object to string!");
        }
        return bodyAsString;
    }
}
