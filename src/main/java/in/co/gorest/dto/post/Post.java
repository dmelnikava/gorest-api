package in.co.gorest.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("user_id")
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
}