package com.sg.driver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sg.baseclass.UserModuleOneBaseClass;
import com.sg.baseclass.UserModuleTwoBaseClass;
import com.sg.common.AppDependentMethods;
import com.sg.common.AppIndependentMethods;
import com.sg.reports.ReportUtils;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import java.util.Map;

@CucumberOptions(
        tags = "@Regression123",
        glue = {"com.sg.stepdefinition"},
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumberReport.html",
                "json:target/cucumber-report/cucumber.json"
        },
        features = {"src/test/resouces/featureFiles/"},
        dryRun = false, monochrome = true
)

public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    public static AppIndependentMethods appInd = null;
    public static AppDependentMethods appDep = null;
    public static ReportUtils report = null;
    public static ExtentReports extent = null;
    public static ExtentTest test = null;
    public static String screenshotLocation = null;
    public static Map<String, String> propData = null;
    public static UserModuleOneBaseClass userOneMethod = null;
    public static UserModuleTwoBaseClass userTwoMethod = null;
    public static WebDriver oBrowser = null;
    public static String userName = null;

    @BeforeSuite
    public void loadClasses(){
        try{
            appInd = new AppIndependentMethods();
            appDep = new AppDependentMethods();
            propData = appInd.getPropData(System.getProperty("user.dir")+"\\src\\main\\resouces\\configuration\\config.properties");
            report = new ReportUtils();
            extent = report.startExtentReport("testReport");
            userOneMethod = new UserModuleOneBaseClass();
            userTwoMethod = new UserModuleTwoBaseClass();
        }catch(Exception e){
            System.out.println("Exception in 'loadClasses()' method. " + e);
        }
    }
}
