package in.co.gorest.gui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.co.gorest.dto.user.User;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class UsersPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//pre")
    private WebElement usersInfo;

    public UsersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public User getRandomUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = new ArrayList<>();
        try {
            users = objectMapper.readValue(usersInfo.getText(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to get users' info!");
        }
        return users.get(new SecureRandom().nextInt(users.size()));
    }
}
