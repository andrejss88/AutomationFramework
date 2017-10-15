package com.dataprovider;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Universal data provider, the number of parameters in the test method just has to match the number of keywords per row
 * Example: if testMethod(param1, param2), then make sure provided CSV file has  "keyword1,keyword2" per row
 * Works with any number of parameters
 *
 * This is a much quicker, leaner and easier to maintain solution than fetching data from Excels with Apache POI library
 */
public class CSVProvider {

    // alternatively the method can return Iterator<Object[]>
    // The @DataProvider must also be marked as returning the same
    public static String[][] getCSVData(String fileName){
        List<Object[]> rows = new ArrayList<>();
        String userDir = System.getProperty("user.dir");
        String pathToFile = userDir + "\\src\\test\\resources\\testdata\\" + fileName;
        try {
            CSVReader reader = new CSVReader(new FileReader(pathToFile));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null){
                rows.add(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Then this must be rows.iterator();
        return rows.toArray(new String[rows.size()][]);
    }
}
