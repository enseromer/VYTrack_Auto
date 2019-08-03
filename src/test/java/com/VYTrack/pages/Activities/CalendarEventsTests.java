package com.VYTrack.pages.Activities;

import com.VYTrack.pages.LoginNavi.LoginPage;
import com.VYTrack.utilities.ConfigReader;
import com.VYTrack.utilities.SeleniumUtils;
import com.VYTrack.utilities.TestBase;
import com.VYTrack.utilities.VYTrackUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class CalendarEventsTests extends TestBase {

    public WebDriver driver;
    @Test
    public void verifyTitleColumn() {
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();
        String username = ConfigReader.getProperty("storemanagerusername");
        String password = ConfigReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);
        //go to Calendar Events page
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        //deselect title option from grid settings
        VYTrackUtils.waitUntilLoaderScreenDisappear(driver);
        calendarPage.selectGridSetting("Title", false);
        SeleniumUtils.waitPlease(3);
        //Verify that title column name is not visible any more
        Assert.assertFalse(calendarPage.verifyHeaderExists("Title"), "Title column name still visible.");
        //to close grid settings menu
        calendarPage.gridSettingsElement.click();
        //select title option again
        calendarPage.selectGridSetting("Title", true);
        //Verify that title column name is visible again
        Assert.assertTrue(calendarPage.verifyHeaderExists("Title"), "Title column is not visible.");
    }
    @Test(description = "Verify that date auto adjustable")
    public void verifyDateTest(){
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();
        String username = ConfigReader.getProperty("storemanagerusername");
        String password = ConfigReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);
        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        calendarPage.clickOnCreateCalendarEvent();
        calendarPage.selectStartOrEndDate("8/15/2019", "start");
        //    verify start date is the same as end date
        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());
    }
    @Test(description = "Verify that date auto adjustable with today's amd tomorrow's date ")
    public void verifyTodaysDateTest(){
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();
        String username = ConfigReader.getProperty("storemanagerusername");
        String password = ConfigReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);
        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        //click to create calendar event
        calendarPage.clickOnCreateCalendarEvent();
        //select tomorrow date
        calendarPage.selectTomorrowDay();
        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());
        //select today's date
        calendarPage.selectTodaysDay();
        //verify that start and end date is the same
        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());
    }
    @Test(description = "Verify that end time changes exactly 1 hours later")
    public void verifyTimeTest(){
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();
        String username = ConfigReader.getProperty("storemanagerusername");
        String password = ConfigReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);
        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        //click to create calendar event
        calendarPage.clickOnCreateCalendarEvent();
        //select any time
        calendarPage.selectStartTime("1:00 PM");
        //Verify that end time changes exactly 1 hours later
        Assert.assertEquals(calendarPage.differenceBetweenStartTimeAndEndTime(), 1);
    }
    @Test(description = "Verify that end time is 12:30 AM")
    public void verifyTimeTest2(){
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();
        String username = ConfigReader.getProperty("storemanagerusername");
        String password = ConfigReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);
        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        //click to create calendar event
        calendarPage.clickOnCreateCalendarEvent();
        //select 11:30 PM
        calendarPage.selectStartTime("11:30 PM");
        //Verify that end time is 12:30 AM
        Assert.assertEquals(calendarPage.getEndTime(), "12:30 AM");
    }
}