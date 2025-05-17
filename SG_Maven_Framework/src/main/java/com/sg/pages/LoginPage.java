package com.sg.pages;

import org.openqa.selenium.By;

public class LoginPage {
    public static By obj_Login_Button = By.xpath("//a[@id='loginButton']");
    public static By obj_UserName_Edit = By.id("username");
    public static By obj_Password_Edit = By.name("pwd");
    public static By obj_Submit_Button = By.xpath("//input[@id='SubmitTTButton']");
    public static By obj_HomePageTitle_Label = By.xpath("//td[@class='pagetitle']");
    public static By obj_ShortCut_Window = By.xpath("//div[@style='display: block;']");
    public static By getObj_ShortCut_Close_Button = By.id("gettingStartedShortcutsMenuCloseId");
    public static By obj_Logout_Link = By.id("logoutLink");
    public static By obj_LoginPage_Lable = By.id("headerContainer");
}
