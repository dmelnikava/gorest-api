package in.co.gorest.api.post.rest;

import in.co.gorest.BaseGorestApiTest;
import in.co.gorest.api.rest.*;
import in.co.gorest.dto.post.Post;
import in.co.gorest.dto.post.PostFactory;
import in.co.gorest.utils.CustomAssert;
import in.co.gorest.utils.NumberGenerator;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class PostTest extends BaseGorestApiTest {

    @Test(description = "Verify GET posts request using page, per_page as query parameters.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-206")
    public void verifyGetPostsInfoUsingQueryParametersTest() {
        QueryParameter postsPageParam = new QueryParameter(QueryOption.PAGE, NumberGenerator.generateInt(1, 10));
        QueryParameter postsPerPageParam = new QueryParameter(QueryOption.PER_PAGE, NumberGenerator.generateInt(1, 100));
        Map<String, Integer> queryParams = Map.of(postsPageParam.getOptionName(), postsPageParam.getValue(),
                postsPerPageParam.getOptionName(), postsPerPageParam.getValue());
        Specification.setOption(URL, queryParams, StatusCode.OK);
        Response responsePostsData = makeRequest(EndpointType.POSTS.getAllRequest());
        List<Post> postsFromResponse = getResponseAsJsonPath(responsePostsData).getList(JSON_ROOT, Post.class);
        CustomAssert.assertJsonSchemaIsValid(responsePostsData, GET_POSTS_RS_PATH);
        Assert.assertEquals(postsFromResponse.size(), postsPerPageParam.getValue(), "Number of posts in the response should equal the value of per_page parameter!");
        Assert.assertEquals(responsePostsData.getHeader(CURRENT_PAGE_NUMBER_HEADER), postsPageParam.getValue().toString(),
                String.format("Response header '%s' should equal 'page' parameter's value!", CURRENT_PAGE_NUMBER_HEADER));
        Assert.assertEquals(responsePostsData.getHeader(RESULTS_PER_PAGE_HEADER), postsPerPageParam.getValue().toString(),
                String.format("Response header '%s' should equal 'per_page' parameter's value!", RESULTS_PER_PAGE_HEADER));
    }

    @Test(description = "Verify POST post request using user id.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-206")
    public void verifyPostRequestUsingUserIdTest() {
        Post post = PostFactory.generatePost(postNewUser());
        Specification.setOption(URL, post, StatusCode.CREATED);
        Pair<RequestType, String> postRequest = EndpointType.POSTS.postRequest();
        Pair<RequestType, String> postPostEndpoint = Pair.of(postRequest.getKey(), String.format(postRequest.getValue(), post.getUserId()));
        Response responsePostData = makeRequest(postPostEndpoint);
        Post postFromResponse = responsePostData.as(Post.class);
        CustomAssert.assertJsonSchemaIsValid(responsePostData, POST_POST_RS_PATH);
        Assert.assertEquals(postFromResponse.getUserId(), post.getUserId(),
                "Post's user id from the response is not equal to post's user id in the request body!");
        Assert.assertEquals(postFromResponse.getTitle(), post.getTitle(),
                "Post's title from the response is not equal to post's title in the request body!");
        Assert.assertEquals(postFromResponse.getBody(), post.getBody(),
                "Post's body from the response is not equal to post's body in the request body!");
    }
}
