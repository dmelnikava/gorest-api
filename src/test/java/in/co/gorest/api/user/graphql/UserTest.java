package in.co.gorest.api.user.graphql;

import in.co.gorest.BaseGorestGraphqlApiTest;
import in.co.gorest.api.graphql.QueryBuilder;
import in.co.gorest.api.rest.*;
import in.co.gorest.dto.user.User;
import in.co.gorest.dto.user.UserFactory;
import in.co.gorest.utils.CustomAssert;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;

import java.util.List;

public class UserTest extends BaseGorestGraphqlApiTest {

    @Test(description = "Verify create user request")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-312")
    public void verifyCreateUserTest() {
        User expectedUser = UserFactory.generateUser();
        String graphqlQuery = QueryBuilder.getGraphqlPayload(CREATE_USER_GRAPHQL_QUERY_PATH, expectedUser);
        Specification.setOptionForRequestToGraphqlApi(graphqlQuery, StatusCode.OK);
        Response responseUserData = makeRequest();
        User userFromResponse = getResponseAsJsonPath(responseUserData).getObject("data.createUser.user", User.class);
        CustomAssert.assertJsonSchemaIsValid(responseUserData, CREATE_USER_GRAPHQL_SCHEMA_PATH);
        Assert.assertEquals(userFromResponse.getName(), expectedUser.getName(), "User's name from the response is not equal to user's name in the request query!");
        Assert.assertEquals(userFromResponse.getEmail(), expectedUser.getEmail(), "User's email from the response is not equal to user's email in the request query!");
        Assert.assertEquals(userFromResponse.getGender(), expectedUser.getGender(), "User's gender from the response is not equal to user's gender in the request query!");
        Assert.assertEquals(userFromResponse.getStatus(), expectedUser.getStatus(), "User's status from the response is not equal to user's status in the request query!");
    }

    @Test(description = "Verify get user by id request")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-313")
    public void verifyGetUserByIDTest() {
        User expectedUser = postNewUser();
        String graphqlQuery = QueryBuilder.getGraphqlPayload(GET_USER_GRAPHQL_QUERY_PATH, expectedUser);
        Specification.setOptionForRequestToGraphqlApi(graphqlQuery, StatusCode.OK);
        Response responseUserData = makeRequest();
        User userFromResponse = getResponseAsJsonPath(responseUserData).getObject("data.user", User.class);
        CustomAssert.assertJsonSchemaIsValid(responseUserData, GET_USER_USER_GRAPHQL_SCHEMA_PATH);
        Assert.assertEquals(userFromResponse, expectedUser, "User's data from the response is not equal to data of user whose id is used in the request!");
    }

    @Test(description = "Verify delete user request")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-316")
    public void verifyDeleteUserTest() {
        User expectedUser = postNewUser();
        String deleteUserQuery = QueryBuilder.getGraphqlPayload(DELETE_USER_GRAPHQL_QUERY_PATH, expectedUser);
        Specification.setOptionForRequestToGraphqlApi(deleteUserQuery, StatusCode.OK);
        Response responseAfterDeleteQuery = makeRequest();
        CustomAssert.assertJsonSchemaIsValid(responseAfterDeleteQuery, DELETE_USER_USER_GRAPHQL_SCHEMA_PATH);
        String getUserQuery = QueryBuilder.getGraphqlPayload(GET_USER_GRAPHQL_QUERY_PATH, expectedUser);
        Specification.setOptionForRequestToGraphqlApi(getUserQuery, StatusCode.OK);
        Response responseAfterGetQuery = makeRequest();
        CustomAssert.assertJsonSchemaIsValid(responseAfterGetQuery, GET_NONEXISTENT_USER_USER_GRAPHQL_SCHEMA_PATH);
        List<List<List<String>>> errorMessages = responseAfterGetQuery.path("errors.extensions.result.messages");
        String actualErrorMessage = errorMessages.get(ZERO).get(ZERO).get(ZERO);
        Assert.assertEquals(actualErrorMessage, String.format(COULDNT_FIND_RECORD_ID, expectedUser.getId()),
                String.format("Message if get request is made after delete request should be [ %s ]!", String.format(COULDNT_FIND_RECORD_ID, expectedUser.getId())));
    }
}
