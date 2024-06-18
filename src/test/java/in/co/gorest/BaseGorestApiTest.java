package in.co.gorest;

import in.co.gorest.api.graphql.QueryBuilder;
import in.co.gorest.api.rest.EndpointType;
import in.co.gorest.api.rest.RequestType;
import in.co.gorest.api.rest.Specification;
import in.co.gorest.api.rest.StatusCode;
import in.co.gorest.constans.IConstants;
import in.co.gorest.dto.comment.Comment;
import in.co.gorest.dto.comment.CommentFactory;
import in.co.gorest.dto.post.Post;
import in.co.gorest.dto.post.PostFactory;
import in.co.gorest.dto.user.User;
import in.co.gorest.dto.user.UserFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;

import static io.restassured.RestAssured.given;

public class BaseGorestApiTest implements IConstants {

    public Response makeRequestToGraphqlApi() {
        return makeRequest(GRAPHQL_ENDPOINT);
    }

    public Response makeRequest(Pair<RequestType, String> endpoint) {
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

    public User postNewUser() {
        User expectedUser = UserFactory.generateUser();
        Specification.setOption(URL, expectedUser, StatusCode.CREATED);
        return makeRequest(EndpointType.USERS.postRequest()).as(User.class);
    }

    public User postNewUserViaGraphqlApi() {
        User user = UserFactory.generateUser();
        String graphqlQuery = QueryBuilder.getGraphqlPayload(CREATE_USER_GRAPHQL_QUERY_PATH, user);
        Specification.setOptionForRequestToGraphqlApi(graphqlQuery, StatusCode.OK);
        return getResponseAsJsonPath(makeRequestToGraphqlApi()).getObject("data.createUser.user", User.class);
    }

    public Post postNewPost() {
        Post post = PostFactory.generatePost(postNewUser());
        Specification.setOption(URL, post, StatusCode.CREATED);
        Pair<RequestType, String> postPostRequest = EndpointType.POSTS.postRequest();
        Pair<RequestType, String> postPostEndpoint = Pair.of(postPostRequest.getKey(),
                String.format(postPostRequest.getValue(), post.getUserId()));
        return makeRequest(postPostEndpoint).as(Post.class);
    }

    public Post postNewPostViaGraphqlApi() {
        User user = postNewUserViaGraphqlApi();
        return postNewPostViaGraphqlApi(user);
    }

    public Post postNewPostViaGraphqlApi(User user) {
        Post post = PostFactory.generatePost(user);
        String graphqlQuery = QueryBuilder.getGraphqlPayload(CREATE_POST_GRAPHQL_QUERY_PATH, post);
        Specification.setOptionForRequestToGraphqlApi(graphqlQuery, StatusCode.OK);
        return getResponseAsJsonPath(makeRequestToGraphqlApi()).getObject("data.createPost.post", Post.class);
    }

    public Comment postNewComment() {
        Post post = postNewPost();
        Comment comment = CommentFactory.generateComment(post);
        Specification.setOption(URL, comment, StatusCode.CREATED);
        Pair<RequestType, String> postCommentRequest = EndpointType.COMMENTS.postRequest();
        Pair<RequestType, String> postCommentEndpoint = Pair.of(postCommentRequest.getKey(),
                String.format(postCommentRequest.getValue(), post.getId()));
        return makeRequest(postCommentEndpoint).as(Comment.class);
    }
}
