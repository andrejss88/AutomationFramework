package com.github.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFactory {

    private final Properties properties = new Properties();
    private String userDir = System.getProperty("user.dir");

    public String loadBaseUrl(){

        String pathToFile =  userDir + "\\src\\main\\resources\\baseurl.properties";

        // https://stackoverflow.com/questions/11430289/how-to-close-the-fileinputstream-while-reading-the-property-file
        try (final FileInputStream fis = new FileInputStream(pathToFile)){
            properties.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found at path: " + pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("baseurl");

    }
}
