package com.github.reporting;

import com.github.CommonConstants;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extent;

    /**
     * Initialize and return extent reporter to manage test report and log
     */
    public static ExtentReports getReporter() {
        if (extent == null) {

            String report = CommonConstants.USER_DIR +"/test-output/STMExtentReport.html";
            extent = new ExtentReports (report, true, DisplayOrder.NEWEST_FIRST);

            extent.addSystemInfo("Browser", "Firefox 48");

            File path = new File(CommonConstants.USER_DIR + "\\src\\test\\resources\\reporting\\extent-config.xml");
            extent.loadConfig(path);
        }

        return extent;
    }

}
