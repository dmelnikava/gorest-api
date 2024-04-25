package in.co.gorest.api.rest;

import in.co.gorest.constans.IConstants;
import org.apache.commons.lang3.tuple.Pair;

public enum EndpointTypes implements IConstants {

    USERS(Pair.of(RequestTypes.GET, USERS_TEXT),
            Pair.of(RequestTypes.GET, USERS_TEXT + "/%s"),
            Pair.of(RequestTypes.POST, USERS_TEXT),
            Pair.of(RequestTypes.DELETE, USERS_TEXT + "/%s"),
            Pair.of(RequestTypes.PUT, USERS_TEXT + "/%s"),
            Pair.of(RequestTypes.PATCH, USERS_TEXT + "/%s")),
    POSTS(Pair.of(RequestTypes.GET, POSTS_TEXT),
            Pair.of(RequestTypes.GET, POSTS_TEXT + "/%s"),
            Pair.of(RequestTypes.POST, USERS_TEXT + "/%s/" + POSTS_TEXT),
            Pair.of(RequestTypes.DELETE, POSTS_TEXT + "/%s"),
            Pair.of(RequestTypes.PUT, POSTS_TEXT + "/%s"),
            Pair.of(RequestTypes.PATCH, POSTS_TEXT + "/%s")),
    COMMENTS(Pair.of(RequestTypes.GET, COMMENTS_TEXT),
            Pair.of(RequestTypes.GET, COMMENTS_TEXT + "/%s"),
            Pair.of(RequestTypes.POST, POSTS_TEXT + "/%s/" + COMMENTS_TEXT),
            Pair.of(RequestTypes.DELETE, COMMENTS_TEXT + "/%s"),
            Pair.of(RequestTypes.PUT, COMMENTS_TEXT + "/%s"),
            Pair.of(RequestTypes.PATCH, COMMENTS_TEXT + "/%s"));

    private final Pair<RequestTypes, String> getAll;
    private final Pair<RequestTypes, String> getOne;
    private final Pair<RequestTypes, String> post;
    private final Pair<RequestTypes, String> delete;
    private final Pair<RequestTypes, String> put;
    private final Pair<RequestTypes, String> patch;

    EndpointTypes(final Pair<RequestTypes, String> getAll, final Pair<RequestTypes, String> getOne, final Pair<RequestTypes, String> post,
                  final Pair<RequestTypes, String> delete, final Pair<RequestTypes, String> put, final Pair<RequestTypes, String> patch) {
        this.getAll = getAll;
        this.getOne = getOne;
        this.post = post;
        this.delete = delete;
        this.put = put;
        this.patch = patch;
    }

    public Pair<RequestTypes, String> getAllRequest() {
        return getAll;
    }

    public Pair<RequestTypes, String> getOneRequest() {
        return getOne;
    }

    public Pair<RequestTypes, String> postRequest() {
        return post;
    }

    public Pair<RequestTypes, String> deleteRequest() {
        return delete;
    }

    public Pair<RequestTypes, String> putRequest() {
        return put;
    }

    public Pair<RequestTypes, String> patchRequest() {
        return patch;
    }
}
