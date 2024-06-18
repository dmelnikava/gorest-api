package in.co.gorest.utils;

import in.co.gorest.constans.IConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

public class ConfigUtil implements IConstants {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static String getConfigValue(String value) {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties.getProperty(value);
        } catch (IOException e) {
            LOGGER.error("Unable to read the file!");
            return "";
        }
    }
}
