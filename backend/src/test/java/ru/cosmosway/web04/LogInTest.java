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

public class LogInTest {
    public static WebDriver driver = new ChromeDriver();
    LogInPage logInPage = new LogInPage(driver);
    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromeDriver"));
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("logInPage"));
    }
    @Test
    public void logInTest() {
        logInPage.inputLogin("testing_login");
        logInPage.inputPassword("testing_password");
        logInPage.logIn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mainPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dotsForm")));
        assertTrue(mainPage.isDisplayed(), "LogInTest failed");

    }
    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
