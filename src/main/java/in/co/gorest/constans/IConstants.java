package in.co.gorest.constans;

import in.co.gorest.utils.ConfigUtil;

public interface IConstants {

    String URL = ConfigUtil.getConfigValue("DEMO.url");
    String SELENIUM_URL = ConfigUtil.getConfigValue("selenium_url");

    String BEARER_TOKEN = ConfigUtil.getConfigValue("bearer_token");

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

    int ZERO = 0;
    int ONE = 1;
}
