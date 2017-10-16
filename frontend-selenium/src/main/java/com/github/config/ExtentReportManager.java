package com.github.config;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

import static com.github.Constants.USER_DIR;

public class ExtentReportManager {

    private static ExtentReports extent;

    /**
     * Initialize and return extent reporter to manage test report and log
     */
    public static ExtentReports getReporter() {
        if (extent == null) {

            String report = USER_DIR +"/test-output/STMExtentReport.html";
            extent = new ExtentReports (report, true, DisplayOrder.NEWEST_FIRST);

            extent.addSystemInfo("Browser", "Firefox 48");

            File path = new File(USER_DIR + "\\src\\test\\resources\\reporting\\extent-config.xml");
            extent.loadConfig(path);
        }

        return extent;
    }

}
