package ru.cosmosway.web04;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private final WebDriver driver;
    @FindBy(xpath = "/html/body/div/main/div/div[1]/form/div/div[1]/div/div/div/label[8]/span[1]/input")
    private WebElement xRadioField;
    @FindBy(xpath = "/html/body/div/main/div/div[1]/form/div/div[2]/div/div/input")
    private WebElement yField;
    @FindBy(xpath = "/html/body/div/main/div/div[1]/form/div/div[3]/div/div/div/label[8]/span[1]/input")
    private WebElement rRadioField;
    @FindBy(xpath = "/html/body/div/main/div/div[1]/form/div/div[4]/div/table/tr/td[1]/button")
    private WebElement sendDataBtn;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputX() {
        xRadioField.click();
    }
    public void inputY(String y) {
        yField.sendKeys(y);
    }
    public void inputR() {
        rRadioField.click();
    }
    public void sendData() {
        sendDataBtn.click();
    }

}

