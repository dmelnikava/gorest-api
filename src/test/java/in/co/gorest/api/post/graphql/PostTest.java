package in.co.gorest.api.post.graphql;

import in.co.gorest.BaseGorestApiTest;
import in.co.gorest.api.graphql.QueryBuilder;
import in.co.gorest.api.rest.Specification;
import in.co.gorest.api.rest.StatusCode;
import in.co.gorest.dto.post.Post;
import in.co.gorest.dto.post.PostFactory;
import in.co.gorest.dto.user.User;
import in.co.gorest.utils.CustomAssert;
import in.co.gorest.utils.NumberGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PostTest extends BaseGorestApiTest {

    @Test(description = "Verify update post's body request")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-317")
    public void verifyUpdatePostBodyTest() {
        Post post = postNewPostViaGraphqlApi();
        Post newPostBody = PostFactory.generateBody(post);
        String graphqlQuery = QueryBuilder.getGraphqlPayload(UPDATE_POST_BODY_GRAPHQL_QUERY_PATH, newPostBody);
        Specification.setOptionForRequestToGraphqlApi(graphqlQuery, StatusCode.OK);
        Response responsePostData = makeRequestToGraphqlApi();
        Post postFromResponse = getResponseAsJsonPath(responsePostData).getObject("data.updatePost.post", Post.class);
        CustomAssert.assertJsonSchemaIsValid(responsePostData, UPDATE_POST_BODY_GRAPHQL_SCHEMA_PATH);
        Assert.assertEquals(postFromResponse, newPostBody, "Post's data from the response is not equal to data of post that id is used in the request!");
    }

    @Test(description = "Verify get all posts' data by user's id request")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-318")
    public void verifyGetAllPostsByUserIdTest() {
        User user = postNewUserViaGraphqlApi();
        List<Post> posts = IntStream.range(1, NumberGenerator.generateInt(2, 10))
                .mapToObj(i -> postNewPostViaGraphqlApi(user))
                .collect(Collectors.toList());
        String graphqlQuery = QueryBuilder.getGraphqlPayload(GET_POSTS_BY_USER_ID_GRAPHQL_QUERY_PATH, user);
        Specification.setOptionForRequestToGraphqlApi(graphqlQuery, StatusCode.OK);
        Response responseData = makeRequestToGraphqlApi();
        List<Post> postsFromResponse = getResponseAsJsonPath(responseData).getList("data.user.posts.nodes", Post.class);
        CustomAssert.assertJsonSchemaIsValid(responseData, GET_POSTS_BY_USER_ID_GRAPHQL_SCHEMA_PATH);
        Assert.assertEquals(postsFromResponse.size(), posts.size(), "Response should contains all user's posts!");
        IntStream.range(0, posts.size()).forEach(i -> {
            Assert.assertEquals(postsFromResponse.get(i), posts.get(i),
                    "Post's data from the response is not equal to the data of post in the request!");
            Assert.assertEquals(postsFromResponse.get(i).getUserId(), user.getId(),
                    String.format("Post's user id is not equal [ %s ] that is used the request!", user.getId()));
        });
    }
}
