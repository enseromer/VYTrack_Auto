package com.VYTrack.tests.Components.LoginNav;

import com.VYTrack.pages.LoginNavi.LoginPage;
import com.VYTrack.utilities.ConfigReader;
import com.VYTrack.utilities.SeleniumUtils;
import com.VYTrack.utilities.TestBase;
import com.VYTrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    @Test
    public void loginTest1(){
        LoginPage loginPage = new LoginPage();
        String username = ConfigReader.getProperty("storemanagerusername"); //configuration filedan gelir
        String password = ConfigReader.getProperty("storemanagerpassword");
        loginPage.clickRememberMe();
        loginPage.login(username, password);
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        //to verify Dahsboard page opened
        //Once page name Dashboard displays , means that we are logged succesfully
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "Dashboard");

    }


    @Test
    public void negativeLoginTest1(){
        LoginPage loginPage = new LoginPage();
        String username = ConfigReader.getProperty("wrongusername");
        String password = ConfigReader.getProperty("wrongpassword");
        loginPage.login(username,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"Invalid user name or password.");
    }
    @Test
    public void loginTest2(){
        //this is required, otherwise you will get null pointer exception
        extentLogger = report.createTest("Login as a store manager");
        //we are instantiating page class inside a tests class,
        //because for second test, if we run all tests in a row, driver will have null session
        LoginPage loginPage = new LoginPage();
        String username = ConfigReader.getProperty("storemanagerusername");
        String password = ConfigReader.getProperty("storemanagerpassword");
        extentLogger.info("Clicking on remember me");
        loginPage.clickRememberMe();
        loginPage.login(username, password);
        //to verify that Dashboard page opened
        //Once page name Dashboard displays, means that we are logged successfully
        Assert.assertEquals(VYTrackUtils.getPageSubTitle(), "Dashboard");
        extentLogger.pass("Verified that page name is Dashboard");
    }
    @Test
    public void negativeLoginTest2(){
        extentLogger = report.createTest("Login with invalid credentials");
        LoginPage loginPage = new LoginPage();
        extentLogger.info("Logging with username: wrongusername, and password: wrongpassword");
        loginPage.login("wrongusername", "wrongpassword");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid user name or password.");
        extentLogger.pass("Verified that warning message displayed: Invalid user name or password.");
    }
}

