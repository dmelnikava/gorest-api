package in.co.gorest;

import in.co.gorest.api.graphql.QueryBuilder;
import in.co.gorest.api.rest.Specification;
import in.co.gorest.api.rest.StatusCode;
import in.co.gorest.dto.post.Post;
import in.co.gorest.dto.post.PostFactory;
import in.co.gorest.dto.user.User;
import in.co.gorest.dto.user.UserFactory;
import io.restassured.response.Response;

public class BaseGorestGraphqlApiTest extends AbstractGorestTest {

    public Response makeRequest() {
        return makeRequest(GRAPHQL_ENDPOINT);
    }

    public User postNewUser() {
        User user = UserFactory.generateUser();
        String graphqlQuery = QueryBuilder.getGraphqlPayload(CREATE_USER_GRAPHQL_QUERY_PATH, user);
        Specification.setOptionForRequestToGraphqlApi(graphqlQuery, StatusCode.OK);
        return getResponseAsJsonPath(makeRequest()).getObject("data.createUser.user", User.class);
    }

    public Post postNewPost() {
        User user = postNewUser();
        return postNewPost(user);
    }

    public Post postNewPost(User user) {
        Post post = PostFactory.generatePost(user);
        String graphqlQuery = QueryBuilder.getGraphqlPayload(CREATE_POST_GRAPHQL_QUERY_PATH, post);
        Specification.setOptionForRequestToGraphqlApi(graphqlQuery, StatusCode.OK);
        return getResponseAsJsonPath(makeRequest()).getObject("data.createPost.post", Post.class);
    }
}
