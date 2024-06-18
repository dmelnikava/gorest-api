package in.co.gorest.constans;

import in.co.gorest.api.rest.RequestType;
import in.co.gorest.utils.ConfigUtil;
import org.apache.commons.lang3.tuple.Pair;

public interface IConstants {

    String URL = ConfigUtil.getConfigValue("DEMO.url");
    String GRAPHQL_URL = ConfigUtil.getConfigValue("graphql_url");
    Pair<RequestType, String> GRAPHQL_ENDPOINT = Pair.of(RequestType.POST, GRAPHQL_URL);

    String BEARER_TOKEN = ConfigUtil.getConfigValue("bearer_token");

    String JSON_CONTENT_TYPE = "application/json";

    String USERS_TEXT = "users";
    String POSTS_TEXT = "posts";
    String COMMENTS_TEXT = "comments";

    String CONFIG_FILE_PATH = "src/main/resources/_config.properties";
    String GET_USERS_RS_PATH = "_get_user_rs.schema";
    String GET_NONEXISTENT_DATA_RS_PATH = "_get_nonexistent_data_rs.schema";
    String POST_USER_RS_PATH = "_post_user_rs.schema";
    String POST_INVALID_DATA_RS_PATH = "_post_invalid_data_rs.schema";
    String PUT_USER_EMAIL_RS_PATH = "_put_user_email_rs.schema";
    String PATCH_USER_NAME_RS_PATH = "_patch_user_name_rs.schema";
    String GET_POSTS_RS_PATH = "_get_post_rs.schema";
    String POST_POST_RS_PATH = "_post_post_rs.schema";
    String POST_COMMENT_RS_PATH = "_post_comment_rs.schema";

    String CREATE_USER_GRAPHQL_QUERY_PATH = "src/test/resources/graphql/create_user.graphql";
    String GET_USER_GRAPHQL_QUERY_PATH = "src/test/resources/graphql/get_user_by_id.graphql";
    String DELETE_USER_GRAPHQL_QUERY_PATH = "src/test/resources/graphql/delete_user_by_id.graphql";
    String CREATE_POST_GRAPHQL_QUERY_PATH = "src/test/resources/graphql/create_post.graphql";
    String UPDATE_POST_BODY_GRAPHQL_QUERY_PATH = "src/test/resources/graphql/update_post_body.graphql";
    String GET_POSTS_BY_USER_ID_GRAPHQL_QUERY_PATH = "src/test/resources/graphql/get_all_posts_by_user_id.graphql";

    String CREATE_USER_GRAPHQL_SCHEMA_PATH = "graphql/schema/_create_user.schema";
    String GET_USER_USER_GRAPHQL_SCHEMA_PATH = "graphql/schema/_get_user_by_id.schema";
    String GET_NONEXISTENT_USER_USER_GRAPHQL_SCHEMA_PATH = "graphql/schema/_get_nonexistent_user_by_id.schema";
    String DELETE_USER_USER_GRAPHQL_SCHEMA_PATH = "graphql/schema/_delete_user_by_id.schema";
    String UPDATE_POST_BODY_GRAPHQL_SCHEMA_PATH = "graphql/schema/_update_post_body.schema";
    String GET_POSTS_BY_USER_ID_GRAPHQL_SCHEMA_PATH = "graphql/schema/_get_all_posts_by_user_id.schema";

    String CURRENT_PAGE_NUMBER_HEADER = "X-Pagination-Page";
    String RESULTS_PER_PAGE_HEADER = "X-Pagination-Limit";

    String EMPTY_STRING = "";
    String JSON_ROOT = ".";
    String EMAIL_TEXT = "email";
    String NAME_TEXT = "name";
    String STATUS_TEXT = "status";
    String FIELD_TEXT = "field";
    String MESSAGE_TEXT = "message";
    String CANT_BE_BLANK_TEXT = "can't be blank";
    String RESOURCE_NOT_FOUND_TEXT = "Resource not found";
    String COULDNT_FIND_RECORD_ID = "Couldn't find record with id=%d";

    int ZERO = 0;
    int ONE = 1;
}
