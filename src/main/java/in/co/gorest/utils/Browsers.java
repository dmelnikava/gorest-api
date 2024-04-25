package in.co.gorest.utils;

import org.openqa.selenium.MutableCapabilities;

public enum Browsers {

    CHROME("chrome"),
    FIREFOX("firefox");

    private final String name;

    Browsers(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MutableCapabilities getCapabilities() {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", getName());
        return capabilities;
    }
}
