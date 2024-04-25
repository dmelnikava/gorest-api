package in.co.gorest.gui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.co.gorest.dto.comment.Comment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class CommentsPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//pre")
    private WebElement commentsInfo;

    public CommentsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Comment getRandomComment() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Comment> comments = new ArrayList<>();
        try {
            comments = objectMapper.readValue(commentsInfo.getText(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to get comments' info!");
        }
        return comments.get(new SecureRandom().nextInt(comments.size()));
    }
}
