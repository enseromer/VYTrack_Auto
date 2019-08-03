package com.VYTrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Synchronize {
    public static void main(String[] args) {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        //here used interface element to call chrome
        driver = new ChromeDriver();
        //I insert implicitly wait in order to handle all kind of the NoSuchElementFound exceptions
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Here I used to maximize method to Maximize the opening website
        driver.manage().window().maximize();
        //here send the webadress link
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-use-explicit-wait-in-selenium.html");
        driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();
        WebDriverWait wait=new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));
        WebElement element = driver.findElement(By.xpath("//p[text()='WebDriver']"));
        boolean status = element.isDisplayed();
        if (status) {
            System.out.println("element is displayed");
        } else {
            System.out.println("element is not displayed");
        }
    }
}
