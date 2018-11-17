package com.github.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ComponentScan("com.github")
public class SpringConfig {

    @Resource(name = "driver")
    protected WebDriver driver;

    @Bean(name = "driver")
    public WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;

    }

    @Bean(name = "wait")
    public WebDriverWait getDriverWait(){
        int waitSeconds = 5;
        return new  WebDriverWait(driver, waitSeconds);
    }
}
