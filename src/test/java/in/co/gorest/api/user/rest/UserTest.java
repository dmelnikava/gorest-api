package in.co.gorest.api.user.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import in.co.gorest.BaseGorestApiTest;
import in.co.gorest.api.rest.*;
import in.co.gorest.dto.user.User;
import in.co.gorest.dto.user.UserFactory;
import in.co.gorest.utils.CustomAssert;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class UserTest extends BaseGorestApiTest {

    @Test(description = "Verify GET user request using id as a query string parameter.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-138")
    public void verifyGetUserInfoByIdTest() {
        User expectedUser = postNewUser();
        QueryParameter userIdParam = new QueryParameter(QueryOption.ID, expectedUser.getId());
        Map<String, Integer> queryParams = Map.of(userIdParam.getOptionName(), userIdParam.getValue());
        Specification.setOption(URL, queryParams, StatusCode.OK);
        Response responseUserData = makeRequest(EndpointType.USERS.getAllRequest());
        List<User> usersFromResponse = getResponseAsJsonPath(responseUserData).getList(JSON_ROOT, User.class);
        CustomAssert.assertJsonSchemaIsValid(responseUserData, GET_USERS_RS_PATH);
        Assert.assertEquals(usersFromResponse.size(), ONE, "Response should contains only one user!");
        Assert.assertEquals(usersFromResponse.get(ZERO).getId(), expectedUser.getId(),
                "User Id from the response does not equal user Id that is used as a query parameter!");
        Assert.assertEquals(usersFromResponse.get(ZERO), expectedUser,
                "User's data from the response is not equal to data of user whose id is used in the request!");
    }

    @Test(description = "Verify the successful creation of a user via the POST request.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-171")
    public void verifyPostUserInfoTest() {
        User expectedUser = UserFactory.generateUser();
        Specification.setOption(URL, expectedUser, StatusCode.CREATED);
        Response responseUserData = makeRequest(EndpointType.USERS.postRequest());
        User userFromResponse = responseUserData.as(User.class);
        CustomAssert.assertJsonSchemaIsValid(responseUserData, POST_USER_RS_PATH);
        Assert.assertEquals(userFromResponse.getName(), expectedUser.getName(),
                "User's name from the response is not equal to user's name in the request body!");
        Assert.assertEquals(userFromResponse.getEmail(), expectedUser.getEmail(),
                "User's email from the response is not equal to user's email in the request body!");
        Assert.assertEquals(userFromResponse.getGender(), expectedUser.getGender(),
                "User's gender from the response is not equal to user's gender in the request body!");
        Assert.assertEquals(userFromResponse.getStatus(), expectedUser.getStatus(),
                "User's status from the response is not equal to user's status in the request body!");
    }

    @Test(description = "Check for failure of user creation using POST request with missing fields.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-203")
    public void verifyPostUserWithMissingFieldTest() {
        User user = UserFactory.generateUser();
        user.setStatus(EMPTY_STRING);
        Specification.setOption(URL, user, StatusCode.UNPROCESSABLE_ENTITY);
        Response responseData = makeRequest(EndpointType.USERS.postRequest());
        List<String> missingFieldName = responseData.path(FIELD_TEXT);
        List<String> actualMessage = responseData.path(MESSAGE_TEXT);
        CustomAssert.assertJsonSchemaIsValid(responseData, POST_INVALID_DATA_RS_PATH);
        Assert.assertEquals(missingFieldName.get(ZERO), STATUS_TEXT,
                String.format("Name of the missing field should be '%s'!", STATUS_TEXT));
        Assert.assertEquals(actualMessage.get(ZERO), CANT_BE_BLANK_TEXT,
                String.format("Message if there is missing field in the body of the request should be '%s'!", CANT_BE_BLANK_TEXT));
    }

    @Test(description = "Verify that the API can successfully delete an existing user.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-203")
    public void verifyDeleteUserInfoTest() {
        User user = postNewUser();
        Specification.setOption(URL, StatusCode.NO_CONTENT);
        Pair<RequestType, String> deleteRequest = EndpointType.USERS.deleteRequest();
        Pair<RequestType, String> deleteUserEndpoint = Pair.of(deleteRequest.getKey(), String.format(deleteRequest.getValue(), user.getId()));
        Response responseAfterDeleteRequest = makeRequest(deleteUserEndpoint);
        Assert.assertTrue(responseAfterDeleteRequest.asString().isEmpty(), "Response body should not contains data if delete request is made!");
        Specification.setOption(URL, StatusCode.NOT_FOUND);
        Pair<RequestType, String> getRequest = EndpointType.USERS.getOneRequest();
        Pair<RequestType, String> getUserEndpoint = Pair.of(getRequest.getKey(), String.format(getRequest.getValue(), user.getId()));
        Response responseAfterGetRequest = makeRequest(getUserEndpoint);
        String actualMessage = responseAfterGetRequest.path(MESSAGE_TEXT);
        CustomAssert.assertJsonSchemaIsValid(responseAfterGetRequest, GET_NONEXISTENT_DATA_RS_PATH);
        Assert.assertEquals(actualMessage, RESOURCE_NOT_FOUND_TEXT,
                String.format("Message if get request is made after delete request should be '%s'!", RESOURCE_NOT_FOUND_TEXT));
    }

    @Test(description = "Verify the successful user data update via the PUT request.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-204")
    public void verifyPutUserInfoTest() {
        User user = postNewUser();
        ObjectNode newUserEmail = UserFactory.generateEmailAsJson();
        Specification.setOption(URL, newUserEmail, StatusCode.OK);
        Pair<RequestType, String> putRequest = EndpointType.USERS.putRequest();
        Pair<RequestType, String> putUserInfoEndpoint = Pair.of(putRequest.getKey(), String.format(putRequest.getValue(), user.getId()));
        Response response = makeRequest(putUserInfoEndpoint);
        User userFromResponse = response.as(User.class);
        CustomAssert.assertJsonSchemaIsValid(response, PUT_USER_EMAIL_RS_PATH);
        Assert.assertEquals(userFromResponse.getEmail(), newUserEmail.findValue(EMAIL_TEXT).textValue(),
                "User's email from the response is not equal to user's email in the request body!");
        Assert.assertEquals(userFromResponse.getId(), user.getId(), "User's id should not have changed after the put request!");
        Assert.assertEquals(userFromResponse.getName(), user.getName(), "User's name should not have changed after the put request!");
        Assert.assertEquals(userFromResponse.getGender(), user.getGender(), "User's gender should not have changed after the put request!");
        Assert.assertEquals(userFromResponse.getStatus(), user.getStatus(), "User's status should not have changed after the put request!");
    }

    @Test(description = "Verify the successful user data update via the PATCH request.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-205")
    public void verifyPatchUserInfoTest() {
        User user = postNewUser();
        ObjectNode newUserName = UserFactory.generateNameAsJson();
        Specification.setOption(URL, newUserName, StatusCode.OK);
        Pair<RequestType, String> patchRequest = EndpointType.USERS.patchRequest();
        Pair<RequestType, String> patchUserInfoEndpoint = Pair.of(patchRequest.getKey(), String.format(patchRequest.getValue(), user.getId()));
        Response response = makeRequest(patchUserInfoEndpoint);
        User userFromResponse = response.as(User.class);
        CustomAssert.assertJsonSchemaIsValid(response, PATCH_USER_NAME_RS_PATH);
        Assert.assertEquals(userFromResponse.getName(), newUserName.findValue(NAME_TEXT).textValue(),
                "User's name from the response is not equal to user's name in the request body!");
        Assert.assertEquals(userFromResponse.getId(), user.getId(), "User's id should not have changed after the patch request!");
        Assert.assertEquals(userFromResponse.getEmail(), user.getEmail(), "User's email should not have changed after the patch request!");
        Assert.assertEquals(userFromResponse.getGender(), user.getGender(), "User's gender should not have changed after the patch request!");
        Assert.assertEquals(userFromResponse.getStatus(), user.getStatus(), "User's status should not have changed after the patch request!");
    }
}
