package in.co.gorest.api.comment.rest;

import in.co.gorest.BaseGorestApiTest;
import in.co.gorest.api.rest.EndpointTypes;
import in.co.gorest.api.rest.RequestTypes;
import in.co.gorest.api.rest.Specification;
import in.co.gorest.api.rest.StatusCodes;
import in.co.gorest.dto.comment.Comment;
import in.co.gorest.dto.comment.CommentFactory;
import in.co.gorest.dto.post.Post;
import in.co.gorest.dto.post.PostFactory;
import in.co.gorest.gui.CommentsPage;
import in.co.gorest.gui.UsersPage;
import in.co.gorest.utils.CustomAssert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;

public class CommentTest extends BaseGorestApiTest {

    @Test(description = "Verify the successful creation of a comment via the POST request.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-206")
    public void verifyPostCommentInfoTest() {
        UsersPage usersPage = new UsersPage(getDriver());
        Post post = PostFactory.generatePost(usersPage.getRandomUser());
        Specification.setOption(URL, post, StatusCodes.CREATED);
        Pair<RequestTypes, String> postPostRequest = EndpointTypes.POSTS.postRequest();
        Pair<RequestTypes, String> postPostEndpoint = Pair.of(postPostRequest.getKey(),
                String.format(postPostRequest.getValue(), post.getUserId()));
        Post postFromResponse = makeRequest(postPostEndpoint).as(Post.class);
        Comment comment = CommentFactory.generateComment(postFromResponse);
        Specification.setOption(URL, comment, StatusCodes.CREATED);
        Pair<RequestTypes, String> postCommentRequest = EndpointTypes.COMMENTS.postRequest();
        Pair<RequestTypes, String> postCommentEndpoint = Pair.of(postCommentRequest.getKey(),
                String.format(postCommentRequest.getValue(), postFromResponse.getId()));
        Response responseCommentData = makeRequest(postCommentEndpoint);
        Comment commentFromResponse = responseCommentData.as(Comment.class);
        CustomAssert.assertJsonSchemaIsValid(responseCommentData, POST_COMMENT_RS_PATH);
        Assert.assertEquals(commentFromResponse.getPostId(), comment.getPostId(),
                "Comment's post id from the response is not equal to comment's post id in the request body!");
        Assert.assertEquals(commentFromResponse.getName(), comment.getName(),
                "Comment's name from the response is not equal to comment's name in the request body!");
        Assert.assertEquals(commentFromResponse.getEmail(), comment.getEmail(),
                "Comment's email from the response is not equal to comment's email in the request body!");
        Assert.assertEquals(commentFromResponse.getBody(), comment.getBody(),
                "Comment's body from the response is not equal to comment's body in the request body!");
    }

    @Test(description = "Verify that the API can successfully delete a comment.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-214")
    public void verifyDeleteCommentInfoTest() {
        CommentsPage commentsPage = new CommentsPage(getDriver());
        Comment comment = commentsPage.getRandomComment();
        Specification.setOption(URL, StatusCodes.NO_CONTENT);
        Pair<RequestTypes, String> deleteRequest = EndpointTypes.COMMENTS.deleteRequest();
        Pair<RequestTypes, String> deleteCommentEndpoint = Pair.of(deleteRequest.getKey(), String.format(deleteRequest.getValue(), comment.getId()));
        Response responseAfterDeleteRequest = makeRequest(deleteCommentEndpoint);
        Assert.assertTrue(responseAfterDeleteRequest.asString().isEmpty(), "Response body should not contains data if delete request is made!");
    }
}
