package com.sg.common;

import com.sg.driver.CucumberTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class AppIndependentMethods extends CucumberTestRunner {
    /***********************************************
     * method Name  : getPropData()
     * Purpose      : It will read prop data in the form of Map<String, String> object
     *
     **********************************************/
    public Map<String, String> getPropData(String strPropFilePath){
        FileInputStream fin = null;
        Properties prop = null;
        Map<String, String> objData = null;
        try{
            objData = new HashMap<String, String>();
            fin = new FileInputStream(strPropFilePath);
            prop = new Properties();
            prop.load(fin);

            Set<Map.Entry<Object, Object>> both = prop.entrySet();
            Iterator<Map.Entry<Object, Object>> it = both.iterator();
            while(it.hasNext() == true){
                Map.Entry<Object, Object> mp = it.next();
                objData.put(mp.getKey().toString(), mp.getValue().toString());
            }
            return objData;
        }catch(Exception e){
            report.writeReport(null, "Exception", "Exception in 'getPropData()' method. " + e);
            return null;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                prop = null;
            }catch(Exception e){}
        }
    }


    /***********************************************
     * method Name  : getDateTime()
     * Purpose      : It will return the timestamp in the required format
     *
     **********************************************/
    public String getDateTime(String dateFormat){
        Date dt = null;
        SimpleDateFormat sdf = null;
        try{
            dt  = new Date();
            sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(dt);
        }catch(Exception e){
            report.writeReport(null, "Exception", "Exception in 'getDateTime()' method. " + e);
            return null;
        }finally{
            dt = null;
            sdf = null;
        }
    }


    /*****************************************************************
     Name        : verifyElementPresent
     Purpose     : To validate the existence of the element in the DOM structure
     *****************************************************************/
    public boolean verifyElementPresent(WebDriver oBrowser, By objBy){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed() == true){
                report.writeReport(oBrowser, "Pass", "The element '"+objBy+"' exist in the DOM successful");
                return true;
            }else{
                report.writeReport(oBrowser, "Fail", "Failed to find the element '"+objBy+"' in the DOM successful");
                return false;
            }
        }catch(Exception e){
            report.writeReport(null, "Exception", "Exception in the 'verifyElementPresent()' method: " + e);
            return false;
        }
    }


    /*****************************************************************
     Name        : verifyElementNotPresent
     Purpose     : To validate the non-existence of the element in the DOM structure
     *****************************************************************/
    public boolean verifyElementNotPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                System.out.println();
                report.writeReport(oBrowser, "Fail", "The element '"+objBy+"' still exist in the DOM");
                return false;
            }else{
                report.writeReport(oBrowser, "Pass", "The element '"+objBy+"' removed from the the DOM successful");
                return true;
            }
        }catch(Exception e){
            report.writeReport(null, "Exception", "Exception in the 'verifyElementNotPresent()' method: " + e);
            return false;
        }
    }



    /*****************************************************************
     Name        : verifyText
     Purpose     : To validate the actual and expected text in the elements
     *****************************************************************/
    public boolean verifyText(WebDriver oBrowser, By objBy, String elementType, String expectedText){
        WebElement oEle = null;
        String actualText = null;
        Select oSelect = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed() == true){
                System.out.println("The Element '"+objBy+"' exist in the DOM successful");
                switch(elementType.toLowerCase()){
                    case "text":
                        actualText = oEle.getText();
                        break;
                    case "value": case "placeholder":
                        actualText = oEle.getDomProperty(elementType);
                        break;
                    case "dropdown": case "visibletext":
                        oSelect = new Select(oEle);
                        actualText = oSelect.getFirstSelectedOption().getText();
                        break;
                    default:
                        report.writeReport(oBrowser, "Fail", "Invalid object type '"+elementType+"' was mentioned.");
                }

                return appInd.compareValues(oBrowser, actualText, expectedText);
            }else{
                report.writeReport(oBrowser, "Fail", "Failed to find the element '"+objBy+"' in the DOM");
                return false;
            }
        }catch(Exception e){
            report.writeReport(null, "Exception", "Exception in the 'verifyText()' method: " + e);
            return false;
        }
    }




    /*****************************************************************
     Name        : clickObject
     Purpose     : To perform click operation on the elements
     *****************************************************************/
    public boolean clickObject(WebDriver oBrowser, By objBy){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.click();
                report.writeReport(oBrowser, "Pass", "The Element '"+objBy+"' was clicked successful");
            }
            return true;
        }catch(Exception e){
            report.writeReport(null, "Exception", "Exception in the 'clickObject()' method: " + e);
            return false;
        }
    }



    /*****************************************************************
     Name        : setObject
     Purpose     : To perform set operation on the elements
     *****************************************************************/
    public boolean setObject(WebDriver oBrowser, By objBy, String data){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.sendKeys(data);
                System.out.println();
                report.writeReport(oBrowser, "Pass", "The data '"+data+"' was set in the Element '"+objBy+"' successful");
            }
            return true;
        }catch(Exception e){
            report.writeReport(null, "Exception", "Exception in the 'setObject()' method: " + e);
            return false;
        }
    }


    /*****************************************************************
     Name        : clearAndSetObject
     Purpose     : To perform set operation on the elements
     *****************************************************************/
    public boolean clearAndSetObject(WebDriver oBrowser, By objBy, String data){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.clear();
                oEle.sendKeys(data);
                report.writeReport(oBrowser, "Pass", "The data '"+data+"' was set in the Element '"+objBy+"' successful");
            }
            return true;
        }catch(Exception e){
            report.writeReport(null, "Exception", "Exception in the 'clearAndSetObject()' method: " + e);
            return false;
        }
    }


    /****************************************************
     * Method Name      : launchBrowser()
     * purpose          : it opens the browsers of mentioned type. Ex: Chrome, Edge and Firefox
     * Author           : tester1
     * Date Created     :
     * Date Modified    :
     * Modified By      :
     * Reviewed By      :
     * Parameters       : String browserName
     * Call Statement   : launchBrowser("Chrome");
     * Return Type      : WebDriver
     ******************************************************/
    public WebDriver launchBrowser(String browserName){
        WebDriver oDriver = null;
        try{
            switch(browserName.toLowerCase()){
                case "chrome":
                    oDriver = new ChromeDriver();
                    break;
                case "edge":
                    oDriver = new EdgeDriver();
                    break;
                case "firefox":
                    oDriver = new FirefoxDriver();
                    break;
                default:
                    report.writeReport(null, "Fail", "Invalid browser name '"+browserName+"' specified");
            }

            if(oDriver!=null){
                report.writeReport(oDriver, "Pass", "The '"+browserName+"' browser launched successful");
                oDriver.manage().window().maximize();
                return oDriver;
            }else{
                report.writeReport(null, "Fail", "Failed to launch the '"+browserName+"' browser.");
                return null;
            }
        }catch(Exception e){
            report.writeReport(null, "Exception", "Exception in the 'launchBrowser()' method: " + e);
            return null;
        }
    }



    /****************************************************
     * Method Name      : waitForElement()
     * purpose          : it waits for the specific condition to match
     * Author           : tester1
     * Parameters       : WebDriver, objBy, waitCondition, text, timeout
     * Call Statement   : waitForElement(oBrowser, By.xpath("//a[@id='loginButton']"), "Clickable", "", 10);
     * Return Type      : boolean
     ******************************************************/
    public boolean waitForElement(WebDriver oBrowser, By objBy, String waitCondition, String text, long timeout){
        WebDriverWait oWait = null;
        try{
            oWait = new WebDriverWait(oBrowser, Duration.ofSeconds(timeout));
            switch(waitCondition.toLowerCase()){
                case "clickable":
                    oWait.until(ExpectedConditions.elementToBeClickable(objBy));
                    break;
                case "text":
                    oWait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, text));
                    break;
                case "invisible":
                    oWait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
                    break;
                case "visible":
                    oWait.until(ExpectedConditions.presenceOfElementLocated(objBy));
                    break;
                default:
                    System.out.println("Invalid wait condition '"+waitCondition+"' was mentioned");
                    return false;
            }
            return true;
        }catch(Exception e){
            //report.writeReport(null, "Exception", "Exception in the 'waitForElement()' method: " + e);
            return false;
        }
    }



    /*****************************************************************
     Name        : compareValues
     Purpose     : To compare both actual and expected values
     *****************************************************************/
    public boolean compareValues(WebDriver oBrowser, String actual, String expected){
        try{
            if(actual.equalsIgnoreCase(expected)){
                report.writeReport(oBrowser, "Pass", "Both actual '"+actual+"' & expected '"+expected+"' values are matching");
                return true;
            }else{
                report.writeReport(oBrowser, "Fail", "Mis-match in both actual '"+actual+"' & expected '"+expected+"' values");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'compareValues()' method: " + e.getMessage());
            return false;
        }
    }


    /*****************************************************************
     Name        : verifyOptionalElement
     Purpose     : To verify the presence of the optional elements
     *****************************************************************/
    public boolean verifyOptionalElement(WebDriver oBrowser, By objBy){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed() == true){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'verifyOptionalElement()' method: " + e.getMessage());
            return false;
        }
    }


    public void threadSleep(long timeOut){
        try{
            Thread.sleep(timeOut);
        }catch(Exception e){
        }
    }

}
