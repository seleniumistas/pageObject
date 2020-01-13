package org.seleniumistas.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumistas.pages.LoginPage;
import org.seleniumistas.pages.LoginSuccessPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyFirstTests {

  private static final String SITE_URL = "http://the-internet.herokuapp.com/login";

  private WebDriver driver;

  @BeforeMethod
  public void setUp() {
    System.getProperty("PATH");
    driver = new ChromeDriver();
    // To work with other browsers:
    // driver = new SafariDriver();
    // driver = new FirefoxDriver();
  }

  @Test(description = "Verify when login is successful \"You logged into a secure area!\" is displayed.")
  public void verifyLoginTest() {
    String username = "tomsmith";
    String password = "SuperSecretPassword!";

    // Go to the test site
    driver.get(SITE_URL);

    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    LoginSuccessPage loginSuccessPage = loginPage.login(username, password);
    String flashText = loginSuccessPage.getFlashMessage();

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
