package in.co.gorest;

import in.co.gorest.api.rest.EndpointType;
import in.co.gorest.api.rest.RequestType;
import in.co.gorest.api.rest.Specification;
import in.co.gorest.api.rest.StatusCode;
import in.co.gorest.dto.comment.Comment;
import in.co.gorest.dto.comment.CommentFactory;
import in.co.gorest.dto.post.Post;
import in.co.gorest.dto.post.PostFactory;
import in.co.gorest.dto.user.User;
import in.co.gorest.dto.user.UserFactory;
import org.apache.commons.lang3.tuple.Pair;

public class BaseGorestApiTest extends AbstractGorestTest {

    public User postNewUser() {
        User expectedUser = UserFactory.generateUser();
        Specification.setOption(URL, expectedUser, StatusCode.CREATED);
        return makeRequest(EndpointType.USERS.postRequest()).as(User.class);
    }

    public Post postNewPost() {
        Post post = PostFactory.generatePost(postNewUser());
        Specification.setOption(URL, post, StatusCode.CREATED);
        Pair<RequestType, String> postPostRequest = EndpointType.POSTS.postRequest();
        Pair<RequestType, String> postPostEndpoint = Pair.of(postPostRequest.getKey(),
                String.format(postPostRequest.getValue(), post.getUserId()));
        return makeRequest(postPostEndpoint).as(Post.class);
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
