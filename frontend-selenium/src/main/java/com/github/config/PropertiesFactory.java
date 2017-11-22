package com.github.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.github.CommonConstants.USER_DIR;

public class PropertiesFactory {

    private final Properties properties = new Properties();


    public String loadBaseUrl(){

        String pathToFile =  USER_DIR + "\\src\\main\\resources\\baseurl.properties";

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
