package org.seleniumistas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

  private static final String USERNAME_INPUT_ID = "username";
  private static final String PASSWORD_INPUT_ID = "password";
  private static final String LOGIN_BUTTON_CSS = "button[type='submit']";

  private WebDriver driver;

  @FindBy(id = USERNAME_INPUT_ID)
  private WebElement usernameInputElement;

  @FindBy(id = PASSWORD_INPUT_ID)
  private WebElement passwordInputElement;

  @FindBy(css = LOGIN_BUTTON_CSS)
  private WebElement loginButtonElement;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  public LoginSuccessPage login(String username, String password) {
    usernameInputElement.sendKeys(username);
    passwordInputElement.sendKeys(password);
    loginButtonElement.click();
    return PageFactory.initElements(driver, LoginSuccessPage.class);
  }


}
