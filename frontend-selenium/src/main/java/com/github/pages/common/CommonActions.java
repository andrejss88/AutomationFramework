package com.github.pages.common;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CommonActions {

    @Resource(name= "driver")
    protected WebDriver driver;

    @Autowired
    private CommonActionsController actions;


    public CommonActionsController act(){
          return actions;
    }

}
