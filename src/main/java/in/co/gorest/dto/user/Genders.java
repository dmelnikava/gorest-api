package in.co.gorest.dto.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Genders {

    FEMALE("female"),
    MALE("male");

    private final String type;

    Genders(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static List<String> getTypes() {
        List<String> types = new ArrayList<>();
        Arrays.stream(Genders.values()).forEach(gender -> types.add(gender.getType()));
        return types;
    }
}
