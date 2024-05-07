package in.co.gorest.dto.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Status {

    ACTIVE("active"),
    INACTIVE("inactive");

    private final String type;

    Status(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static List<String> getTypes() {
        List<String> types = new ArrayList<>();
        Arrays.stream(Status.values()).forEach(status -> types.add(status.getType()));
        return types;
    }
}
