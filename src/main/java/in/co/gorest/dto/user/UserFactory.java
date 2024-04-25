package in.co.gorest.dto.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

import static in.co.gorest.constans.IConstants.*;

public class UserFactory {

    public static User generateUser() {
        User user = new User();
        generateName(user);
        generateEmail(user);
        generateGender(user);
        generateStatus(user);
        return user;
    }

    public static User generateName(User user) {
        user.setName(String.format("%s %s", RandomStringUtils.randomAlphabetic(2, 10), RandomStringUtils.randomAlphabetic(2, 15)));
        return user;
    }

    public static ObjectNode generateNameAsJson() {
        User newUserData = UserFactory.generateName(new User());
        ObjectNode newUserDataAsJson = new ObjectMapper().createObjectNode();
        newUserDataAsJson.put(NAME_TEXT, newUserData.getName());
        return newUserDataAsJson;
    }

    public static User generateEmail(User user) {
        user.setEmail(String.format("%s@%s.test", RandomStringUtils.randomAlphabetic(10, 15), RandomStringUtils.randomAlphabetic(5, 10)));
        return user;
    }

    public static ObjectNode generateEmailAsJson() {
        User newUserData = UserFactory.generateEmail(new User());
        ObjectNode newUserDataAsJson = new ObjectMapper().createObjectNode();
        newUserDataAsJson.put(EMAIL_TEXT, newUserData.getEmail());
        return newUserDataAsJson;
    }

    public static User generateGender(User user) {
        user.setGender(Genders.getTypes().get(new SecureRandom().nextInt(2)));
        return user;
    }

    public static User generateStatus(User user) {
        user.setStatus(Statuses.getTypes().get(new SecureRandom().nextInt(2)));
        return user;
    }
}
