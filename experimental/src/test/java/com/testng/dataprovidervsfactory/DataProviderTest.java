package com.testng.dataprovidervsfactory;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @DataProvider
    public static Object[][] numberProvider() {
        return new Object[][]{
                {1},
                {2}
        };
    }

    // DataProvider provides data to a SINGLE method
    @Test(dataProvider = "numberProvider")
    public void test1(int num){
        Assert.assertNotEquals(3, num);
    }

    @Test(dataProvider = "numberProvider")
    public void test2(int num){
        // ...
    }

    @Test(dataProvider = "numberProvider")
    public void test3(int num){
        // ...
    }

    // Hmmm... I still have 10 tests to write here,
    // and it's getting annoying to specify the dataprovider everytime...
}
