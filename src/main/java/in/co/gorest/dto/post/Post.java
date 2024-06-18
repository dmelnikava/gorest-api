package in.co.gorest.dto.post;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Post {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("user_id")
    @JsonAlias("userId")
    public Integer userId;
    @JsonProperty("title")
    public String title;
    @JsonProperty("body")
    public String body;

    public Post() {
        super();
    }

    public Post(Integer id, Integer userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id)
                && Objects.equals(userId, post.userId)
                && Objects.equals(title, post.title)
                && Objects.equals(body, post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, body);
    }
}
