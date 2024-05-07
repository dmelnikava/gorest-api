package in.co.gorest.api.rest;

public enum QueryOption {

    ID("id"),
    PAGE("page"),
    PER_PAGE("per_page");

    private final String name;

    QueryOption(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
