package ru.cosmosway.web04;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    private final WebDriver driver;
    @FindBy(xpath = "/html/body/div/main/div/div/form/div/div[1]/div/div/input")
    private WebElement loginField;
    @FindBy(xpath = "/html/body/div/main/div/div/form/div/div[2]/div/div/input")
    private WebElement passwordField;
    @FindBy(xpath = "/html/body/div/main/div/div/form/div/div[3]/div/button")
    private WebElement logInBtn;
    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }
    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }
    public void logIn() {
        logInBtn.click();
    }

}
