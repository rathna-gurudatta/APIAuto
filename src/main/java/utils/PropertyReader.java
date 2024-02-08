package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    //to read property file we do not need any external dependencies, since we are using java.util package

    public static String propertyReader(String filePath, String key) throws IOException {
        String value = null;
        InputStream file = new FileInputStream(filePath);
        Properties prop = new Properties();
        prop.load(file);
        value = prop.getProperty(key);
        return value;

    }


}
