package in.co.gorest.dto.post;

import in.co.gorest.dto.post.Post;
import in.co.gorest.dto.user.User;
import org.apache.commons.lang3.RandomStringUtils;

public class PostFactory {

    public static Post generatePost(User user) {
        Post post = new Post();
        post.setUserId(user.getId());
        generateTitle(post);
        generateBody(post);
        return post;
    }

    public static Post generateTitle(Post post) {
        post.setTitle(RandomStringUtils.randomAlphabetic(10, 15));
        return post;
    }

    public static Post generateBody(Post post) {
        post.setBody(RandomStringUtils.randomAlphabetic(10, 15));
        return post;
    }
}
