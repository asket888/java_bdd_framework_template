package utils;

import org.junit.Assert;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyGetterUtil {

    public static String getPropertyValue(String propertyName) {
        Properties property = new Properties();
        String propertyFileName = "config.properties";

        try {
            InputStream inputStream = new FileInputStream(System.getProperty("user.dir")
                    + "\\src\\test\\resources\\config\\" + propertyFileName);
            property.load(inputStream);
        } catch (Exception e) {
            Assert.fail("Unable to read properties file" + e);
        }

        String propertyValue = property.getProperty(propertyName);
        if (propertyValue == null) {
            Assert.fail("Property name not found in config.properties file");
        }

        return propertyValue;
    }
}
