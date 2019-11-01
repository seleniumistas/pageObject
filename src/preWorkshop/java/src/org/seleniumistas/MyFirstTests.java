package org.seleniumistas;

import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyFirstTests {
  private static final String SITE_URL = "http://the-internet.herokuapp.com/login";
  private static final String USERNAME_INPUT_ID = "username";
  private static final String PASSWORD_INPUT_ID = "password";
  private static final String LOGIN_BUTTON_CSS = "button.radius";
  private static final String FLASH_MSG_TEXT_ID = "flash";

  private WebDriver driver;

  @BeforeTest
  public void setUp() {
    // Assumes chromedriver is saved in <user_home>/drivers
    String chromeDriver =
        System.getProperty("os.name").equalsIgnoreCase("Win") ? "chromedriver.exe" : "chromedriver";
    String chromeDriverPath =
        Paths.get(System.getProperty("user.home"), "drivers", chromeDriver).toString();

    // Optional, if not specified, WebDriver will search your path for chromedriver.
    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    driver = new ChromeDriver();
  }

  @Test(description = "Verify when login is successful, Logout button is displayed.")
  public void verifyLoginTest() {
    String username = "tomsmith";
    String password = "SuperSecretPassword!";

    // Go to the test site
    driver.get(SITE_URL);

    // Find username input field and enter username
    WebElement usernameInputElement = driver.findElement(By.id(USERNAME_INPUT_ID));
    usernameInputElement.sendKeys(username);

    // Find password input filed and enter password
    WebElement passwordInputElement = driver.findElement(By.id(PASSWORD_INPUT_ID));
    passwordInputElement.sendKeys(password);

    // Find Login button and click it
    WebElement loginButtonElement = driver.findElement(By.cssSelector(LOGIN_BUTTON_CSS));
    loginButtonElement.click();

    // Find flash test and get text
    String flashText = driver.findElement(By.id(FLASH_MSG_TEXT_ID)).getText();
    Assert.assertTrue(
        flashText.contains("You logged into a secure area!"),
        "Message is incorrect, should contain \"You logged into a secure area!\"");
  }

  @AfterMethod
  public void tearDown() {
    // terminate webdriver
    driver.quit();
  }
}
