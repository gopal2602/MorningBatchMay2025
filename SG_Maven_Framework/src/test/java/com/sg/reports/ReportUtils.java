package com.sg.reports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sg.driver.CucumberTestRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;

public class ReportUtils extends CucumberTestRunner {
    /***********************************************
     * method Name  : startExtentReport()
     * Purpose      : It will create a report file location and .html file for report
     *
     **********************************************/
    public ExtentReports startExtentReport(String reportFileName){
        String resultPath = null;
        File objResultPath = null;
        File objScreenshot = null;
        try{
            resultPath = System.getProperty("user.dir") + "\\target\\extent-report";

            objResultPath = new File(resultPath);
            if(!objResultPath.exists()){
                objResultPath.mkdirs();
            }

            screenshotLocation = resultPath + "\\screenshot";
            objScreenshot = new File(screenshotLocation);
            if(!objScreenshot.exists()){
                objScreenshot.mkdirs();
            }

            extent = new ExtentReports(resultPath +"\\"+ reportFileName + ".html", false);
            extent.addSystemInfo("Host Name", System.getProperty("os.name"));
            extent.addSystemInfo("User Name", System.getProperty("user.name"));
            extent.addSystemInfo("Environment", propData.get("environment"));
            extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
            return extent;
        }catch(Exception e){
            System.out.println("Exception in 'startExtentReport()' method. " + e);
            return null;
        }
    }


    /***********************************************
     * method Name  : endExtentReport()
     * Purpose      : It will end the extent report
     **********************************************/
    public void endExtentReport(ExtentTest test){
        try{
            extent.endTest(test);
            extent.flush();
        }catch(Exception e){
            System.out.println("Exception in 'endExtentReport()' method. " + e);
        }
    }


    /***********************************************
     * method Name  : captureScreenshot()
     * Purpose      : It will capture the screenshot
     **********************************************/
    public String captureScreenshot(WebDriver oBrowser){
        File objSrc = null;
        File objDesc = null;
        String fileLocation = null;
        try{
            fileLocation = screenshotLocation + "\\screenshot_" + appInd.getDateTime("hhmmsS")+".png";
            objSrc = ((TakesScreenshot) oBrowser).getScreenshotAs(OutputType.FILE);
            objDesc = new File(fileLocation);
            FileHandler.copy(objSrc, objDesc);
            return fileLocation;
        }catch(Exception e){
            System.out.println("Exception in 'captureScreenshot()' method. " + e);
            return null;
        }
    }


    /***********************************************
     * method Name  : writeReport()
     * Purpose      : It will write the report in the .html file
     **********************************************/
    public void writeReport(WebDriver oBrowser, String status, String description){
        try{
            switch(status.toLowerCase()){
                case "pass":
                    test.log(LogStatus.PASS, description);
                    break;
                case "fail":
                    test.log(LogStatus.FAIL, description + " " + test.addScreenCapture(report.captureScreenshot(oBrowser)));
                    break;
                case "warning":
                    test.log(LogStatus.WARNING, description);
                    break;
                case "exception":
                    test.log(LogStatus.FATAL, description + " " + test.addScreenCapture(report.captureScreenshot(oBrowser)));
                    break;
                case "screenshot":
                    test.log(LogStatus.PASS, description + " " + test.addScreenCapture(report.captureScreenshot(oBrowser)));
                    break;
                default:
                    System.out.println("Invalid status '"+status+"' for the results");
            }
        }catch(Exception e){
            System.out.println("Exception in 'writeReport()' method. " + e);
        }
    }
}
