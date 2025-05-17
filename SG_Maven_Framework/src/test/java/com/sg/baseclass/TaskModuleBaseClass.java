package com.sg.baseclass;

import com.sg.driver.CucumberTestRunner;
import com.sg.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class TaskModuleBaseClass extends CucumberTestRunner {
    /****************************************************
     * Method Name       : navigateURL()
     * purpose          : it loads the given URL
     * Author           : tester1
     * Parameters       : WebDriver, URL, Title
     * Call Statement   : navigateURL(WebDriver, "http://localhost/login.do");
     * Return Type      : boolean
     ******************************************************/
    public boolean navigateURL(WebDriver oBrowser, String strURL, String title){
        try{
            oBrowser.navigate().to(strURL);
            appInd.waitForElement(oBrowser, LoginPage.obj_Login_Button, "Clickable", "", 10);
            return appInd.compareValues(oBrowser, oBrowser.getTitle(), title);
        }catch(Exception e){
            report.writeReport(oBrowser, "Exception", "Exception in 'navigateURL()' method: " + e.getMessage());
            return false;
        }
    }
}
