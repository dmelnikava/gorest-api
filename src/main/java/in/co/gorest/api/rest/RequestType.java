package in.co.gorest.api.rest;

public enum RequestType {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    private final String name;

    RequestType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
