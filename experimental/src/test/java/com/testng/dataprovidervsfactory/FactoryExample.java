package com.testng.dataprovidervsfactory;

import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryExample {

    @Factory
    public Object[] factoryMethod() {
        return new Object[] {
                new FactoryExample(0),
                new FactoryExample(1) };
    }

    private int number;

    private FactoryExample(){}

    private FactoryExample(int number) {
        this.number = number;
    }

    // Now there's no need to specify dataproviders everywhere, nice
    @Test
    public void test1(){
        Assert.assertNotEquals(3, number);
    }

    @Test
    public void test2(){
       // ...
    }
}
