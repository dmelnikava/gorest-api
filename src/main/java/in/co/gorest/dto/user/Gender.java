package in.co.gorest.dto.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Gender {

    FEMALE("female"),
    MALE("male");

    private final String type;

    Gender(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static List<String> getTypes() {
        List<String> types = new ArrayList<>();
        Arrays.stream(Gender.values()).forEach(gender -> types.add(gender.getType()));
        return types;
    }
}
