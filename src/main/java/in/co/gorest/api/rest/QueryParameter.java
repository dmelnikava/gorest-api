package in.co.gorest.api.rest;

public class QueryParameter {

    private QueryOption option;
    private Integer value;

    public QueryParameter() {
        super();
    }

    public QueryParameter(QueryOption option, Integer value) {
        this.option = option;
        this.value = value;
    }

    public void setOption(QueryOption option) {
        this.option = option;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public QueryOption getOption() {
        return option;
    }

    public Integer getValue() {
        return value;
    }

    public String getOptionName() {
        return option.getName();
    }
}
