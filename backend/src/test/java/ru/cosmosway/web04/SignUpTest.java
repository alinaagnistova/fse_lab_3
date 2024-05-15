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

public class SignUpTest {
    public static WebDriver driver = new ChromeDriver();
    SignUpPage signUpPage = new SignUpPage(driver);
    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromeDriver"));
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("signUpPage"));
    }
    @Test
    public void signUpTest() {
        signUpPage.inputLogin("testing_login");
        signUpPage.inputPassword("testing_password");
        signUpPage.inputConfirmPassword("testing_password");
        signUpPage.signUp();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_form")));
        assertTrue(loginPage.isDisplayed(), "SignUpTest failed");

    }
    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
