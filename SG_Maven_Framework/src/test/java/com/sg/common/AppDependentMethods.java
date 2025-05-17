package com.sg.common;

import com.sg.driver.CucumberTestRunner;
import com.sg.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AppDependentMethods extends CucumberTestRunner {
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



    /****************************************************
     * Method Name      : loginToActime()
     * purpose          : it will login to ActiTime
     * Author           : tester1
     * Parameters       : WebDriver, userName, Password
     * Call Statement   : loginToActime(oBrowser, "admin", "manager");
     * Return Type      : boolean
     ******************************************************/
    public boolean loginToActiTime(WebDriver oBrowser, String userName, String password){
        try{
            Assert.assertTrue(appInd.setObject(oBrowser, LoginPage.obj_UserName_Edit, userName));
            Assert.assertTrue(appInd.setObject(oBrowser, LoginPage.obj_Password_Edit, password));
            Assert.assertTrue(appInd.clickObject(oBrowser, LoginPage.obj_Login_Button));

            appInd.waitForElement(oBrowser, LoginPage.obj_Submit_Button, "Clickable", "", 10);

            boolean blnRes = appInd.verifyText(oBrowser, LoginPage.obj_HomePageTitle_Label, "Text", "Enter Time-Track");
            if(blnRes==true){
                report.writeReport(oBrowser, "Pass", "Login to actiTime is successful");
                oBrowser.navigate().refresh();
                appInd.waitForElement(oBrowser, LoginPage.obj_Submit_Button, "Clickable", "", 10);

                if(appInd.verifyOptionalElement(oBrowser, LoginPage.obj_ShortCut_Window)){
                    Assert.assertTrue(appInd.clickObject(oBrowser, LoginPage.getObj_ShortCut_Close_Button));
                }
                return true;
            }else{
                report.writeReport(oBrowser, "Fail", "Failed to Login to actiTime application");
                return false;
            }
        }catch(Exception e){
            report.writeReport(oBrowser, "Exception", "Exception in 'loginToActiTime()' method: " + e.getMessage());
            return false;
        }
    }


    /****************************************************
     * Method Name      : logoutFromActiTime()
     * purpose          : it will logout from ActiTime
     * Author           : tester1
     * Parameters       : WebDriver
     * Call Statement   : logoutFromActiTime(oBrowser);
     * Return Type      : boolean
     ******************************************************/
    public boolean logoutFromActiTime(WebDriver oBrowser){
        try{
            Assert.assertTrue(appInd.clickObject(oBrowser, LoginPage.obj_Logout_Link));
            appInd.waitForElement(oBrowser, LoginPage.obj_LoginPage_Lable, "Text", "Please identify yourself", 10);

            if(appInd.verifyText(oBrowser, LoginPage.obj_LoginPage_Lable, "Text", "Please identify yourself")){
                report.writeReport(oBrowser, "Pass", "Logout from actiTime is successful");
                return true;
            }else {
                report.writeReport(oBrowser, "Fail", "Failed to logout from the ActiTime");
                return false;
            }
        }catch(Exception e){
            System.out.println();
            report.writeReport(oBrowser, "Exception", "Exception in 'logoutFromActiTime()' method: " + e.getMessage());
            return false;
        }
    }

}
