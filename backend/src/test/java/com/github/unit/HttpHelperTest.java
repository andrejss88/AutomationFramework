package com.github.unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.annotations.Test;

import static com.github.utils.HttpHelper.valueIsPresent;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

@Test(groups = "Unit")
public class HttpHelperTest {

    private final String key = "name";
    private final String value = "LQuiz";

    @Test
    public void valueIsPresentReturnsFalseWithEmptyArray() throws JSONException {

        String data = "[]";
        JSONArray jsonArr = new JSONArray(data);

        assertFalse(valueIsPresent(jsonArr, key,value));
    }

    @Test
    public void valuesIsPresentReturnsFalseWithArrayNotHavingElement() throws JSONException {

        String data = "[ { \"name\":\"playground\",\"has_wiki\":true,}]";
        JSONArray jsonArr = new JSONArray(data);

        assertFalse(valueIsPresent(jsonArr, key,value));
    }

    @Test
    public void valuesIsPresentReturnsTrueWithArrayHavingElement() throws JSONException {

        String data = "[ { \"name\":\"playground\",\"has_wiki\":true,}, { \"name\":\"LQuiz\",\"has_wiki\":true,}]";
        JSONArray jsonArr = new JSONArray(data);

        assertTrue(valueIsPresent(jsonArr, key,value));

    }





}
