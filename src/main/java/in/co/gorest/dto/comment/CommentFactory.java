package in.co.gorest.dto.comment;

import in.co.gorest.dto.post.Post;
import org.apache.commons.lang3.RandomStringUtils;

public class CommentFactory {

    public static Comment generateComment(Post post) {
        Comment comment = new Comment();
        comment.setPostId(post.getId());
        generateName(comment);
        generateEmail(comment);
        generateBody(comment);
        return comment;
    }

    public static Comment generateName(Comment comment) {
        comment.setName(String.format("%s %s", RandomStringUtils.randomAlphabetic(2, 10), RandomStringUtils.randomAlphabetic(2, 15)));
        return comment;
    }

    public static Comment generateEmail(Comment comment) {
        comment.setEmail(String.format("%s@%s.test", RandomStringUtils.randomAlphabetic(10, 15), RandomStringUtils.randomAlphabetic(5, 10)));
        return comment;
    }

    public static Comment generateBody(Comment comment) {
        comment.setBody(RandomStringUtils.randomAlphabetic(10, 15));
        return comment;
    }
}
