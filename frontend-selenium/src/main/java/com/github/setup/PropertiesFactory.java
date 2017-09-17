package com.github.setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFactory {

    private static final Properties properties = new Properties();
    private static String userDir = System.getProperty("user.dir");

    public static String loadBaseUrl(){

        String pathToFile =  userDir + "\\src\\main\\resources\\baseurl.properties";

        try {
            properties.load(new FileInputStream(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("baseurl");

    }
}
