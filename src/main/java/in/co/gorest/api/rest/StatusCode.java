package in.co.gorest.api.rest;

public enum StatusCode {

    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    NOT_FOUND(404),
    UNPROCESSABLE_ENTITY(422);

    private final int value;

    StatusCode(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
