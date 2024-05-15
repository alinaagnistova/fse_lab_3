package ru.cosmosway.web04;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCoordinatesTest {
    public static WebDriver driver = new ChromeDriver();
    static MainPage mainPage;
    static LogInPage logInPage;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromeDriver"));
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("logInPage"));
        logInPage = new LogInPage(driver);
        logInPage.inputLogin("testing_login");
        logInPage.inputPassword("testing_password");
        logInPage.logIn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dotsForm")));
        driver.get(ConfProperties.getProperty("mainPage"));
        mainPage = new MainPage(driver);
    }
    @Test
    public void addCoordinatesTest() {
        mainPage.inputX();
        mainPage.inputY("3");
        mainPage.inputR();
        mainPage.sendData();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
        WebElement mainPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("data_row_0")));
        assertTrue(mainPage.isDisplayed(), "AddCoordinatesTest failed");

    }
    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
