package com.sg.stepdefinition;

import com.sg.driver.CucumberTestRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends CucumberTestRunner {
    @Before
    public void setUp(Scenario scenario){
        String scenarioName = null;
        try{
            scenarioName = scenario.getName();
            test = extent.startTest(scenarioName);
            oBrowser = appInd.launchBrowser("Chrome");
        }catch(Exception e){
            System.out.println("Exception in 'setUp()' method. " + e);
        }
    }

    @After
    public void tearDown(Scenario scenario){
        String scenarioName = null;
        try{
            scenarioName = scenario.getName().replace(" ", "");
            if(scenario.isFailed() == true){
                TakesScreenshot ts = (TakesScreenshot) oBrowser;
                byte[] screen = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screen, "image/png", scenarioName);
            }
            report.endExtentReport(test);
            oBrowser.close();
            oBrowser = null;
        }catch(Exception e){
            System.out.println("Exception in 'tearDown()' method. " + e);
        }
    }
}
