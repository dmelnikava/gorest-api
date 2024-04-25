package in.co.gorest.api.rest;

public enum RequestTypes {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    private final String name;

    RequestTypes(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
