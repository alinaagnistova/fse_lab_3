package ru.cosmosway.web04;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    private final WebDriver driver;
    @FindBy(xpath = "/html/body/div/main/div/div/form/div/div[1]/div/div/input")
    private WebElement loginField;
    @FindBy(xpath = "/html/body/div/main/div/div/form/div/div[2]/div/div/input")
    private WebElement passwordField;
    @FindBy(xpath = "/html/body/div/main/div/div/form/div/div[3]/div/div/input")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "/html/body/div/main/div/div/form/div/div[4]/div/button")
    private WebElement signUpBtn;
    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }
    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }
    public void inputConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }
    public void signUp() {
        signUpBtn.click();
    }

}
