package in.co.gorest.api.comment.rest;

import in.co.gorest.BaseGorestApiTest;
import in.co.gorest.api.rest.EndpointType;
import in.co.gorest.api.rest.RequestType;
import in.co.gorest.api.rest.Specification;
import in.co.gorest.api.rest.StatusCode;
import in.co.gorest.dto.comment.Comment;
import in.co.gorest.dto.comment.CommentFactory;
import in.co.gorest.dto.post.Post;
import in.co.gorest.utils.CustomAssert;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.Test;

public class CommentTest extends BaseGorestApiTest {

    @Test(description = "Verify the successful creation of a comment via the POST request.")
    @CustomAttribute(name = "TestCaseKey", values = "ANDK-206")
    public void verifyPostCommentInfoTest() {
        Post post = postNewPost();
        Comment comment = CommentFactory.generateComment(post);
        Specification.setOption(URL, comment, StatusCode.CREATED);
        Pair<RequestType, String> postCommentRequest = EndpointType.COMMENTS.postRequest();
        Pair<RequestType, String> postCommentEndpoint = Pair.of(postCommentRequest.getKey(),
                String.format(postCommentRequest.getValue(), post.getId()));
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
        Comment comment = postNewComment();
        Specification.setOption(URL, StatusCode.NO_CONTENT);
        Pair<RequestType, String> deleteRequest = EndpointType.COMMENTS.deleteRequest();
        Pair<RequestType, String> deleteCommentEndpoint = Pair.of(deleteRequest.getKey(), String.format(deleteRequest.getValue(), comment.getId()));
        Response responseAfterDeleteRequest = makeRequest(deleteCommentEndpoint);
        Assert.assertTrue(responseAfterDeleteRequest.asString().isEmpty(), "Response body should not contains data if delete request is made!");
    }
}
