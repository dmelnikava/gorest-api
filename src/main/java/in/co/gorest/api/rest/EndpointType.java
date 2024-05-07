package in.co.gorest.api.rest;

import in.co.gorest.constans.IConstants;
import org.apache.commons.lang3.tuple.Pair;

public enum EndpointType implements IConstants {

    USERS(Pair.of(RequestType.GET, USERS_TEXT),
            Pair.of(RequestType.GET, USERS_TEXT + "/%s"),
            Pair.of(RequestType.POST, USERS_TEXT),
            Pair.of(RequestType.DELETE, USERS_TEXT + "/%s"),
            Pair.of(RequestType.PUT, USERS_TEXT + "/%s"),
            Pair.of(RequestType.PATCH, USERS_TEXT + "/%s")),
    POSTS(Pair.of(RequestType.GET, POSTS_TEXT),
            Pair.of(RequestType.GET, POSTS_TEXT + "/%s"),
            Pair.of(RequestType.POST, USERS_TEXT + "/%s/" + POSTS_TEXT),
            Pair.of(RequestType.DELETE, POSTS_TEXT + "/%s"),
            Pair.of(RequestType.PUT, POSTS_TEXT + "/%s"),
            Pair.of(RequestType.PATCH, POSTS_TEXT + "/%s")),
    COMMENTS(Pair.of(RequestType.GET, COMMENTS_TEXT),
            Pair.of(RequestType.GET, COMMENTS_TEXT + "/%s"),
            Pair.of(RequestType.POST, POSTS_TEXT + "/%s/" + COMMENTS_TEXT),
            Pair.of(RequestType.DELETE, COMMENTS_TEXT + "/%s"),
            Pair.of(RequestType.PUT, COMMENTS_TEXT + "/%s"),
            Pair.of(RequestType.PATCH, COMMENTS_TEXT + "/%s"));

    private final Pair<RequestType, String> getAll;
    private final Pair<RequestType, String> getOne;
    private final Pair<RequestType, String> post;
    private final Pair<RequestType, String> delete;
    private final Pair<RequestType, String> put;
    private final Pair<RequestType, String> patch;

    EndpointType(final Pair<RequestType, String> getAll, final Pair<RequestType, String> getOne, final Pair<RequestType, String> post,
                 final Pair<RequestType, String> delete, final Pair<RequestType, String> put, final Pair<RequestType, String> patch) {
        this.getAll = getAll;
        this.getOne = getOne;
        this.post = post;
        this.delete = delete;
        this.put = put;
        this.patch = patch;
    }

    public Pair<RequestType, String> getAllRequest() {
        return getAll;
    }

    public Pair<RequestType, String> getOneRequest() {
        return getOne;
    }

    public Pair<RequestType, String> postRequest() {
        return post;
    }

    public Pair<RequestType, String> deleteRequest() {
        return delete;
    }

    public Pair<RequestType, String> putRequest() {
        return put;
    }

    public Pair<RequestType, String> patchRequest() {
        return patch;
    }
}
