package com.VYTrack.pages.LoginNavi;

import com.VYTrack.utilities.ConfigReader;
import com.VYTrack.utilities.Driver;
import com.VYTrack.utilities.VYTrackUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    //we
    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Long.valueOf(ConfigReader.getProperty("explicitwait")));
    @FindBy(id = "prependedInput")
    public WebElement userNameElement;
    @FindBy(name = "_password")
    public WebElement passwordElement;
    @FindBy(id="_submit")
    public WebElement loginButtonElement;
    @FindBy(className = "custom-checkbox__icon")
    public WebElement rememberMeElement;
    @FindBy(partialLinkText = "Forgot your password?")
    public WebElement forgotPasswordElement;
    @FindBy(tagName = "h2")
    public WebElement titleElement;
    @FindBy(css = "[class='alert alert-error'] > div")
    public WebElement errorMessageElement;
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    public void login(String username, String password){
        userNameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        loginButtonElement.click();
        VYTrackUtils.waitUntilLoaderScreenDisappear(Driver.getDriver());
    }
    public String getErrorMessage(){
        return errorMessageElement.getText();
    }
    public void clickRememberMe(){
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeElement));
        if(!rememberMeElement.isSelected()){
            rememberMeElement.click();
        }
    }
}
