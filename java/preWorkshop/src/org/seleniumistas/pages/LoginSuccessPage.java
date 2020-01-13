package org.seleniumistas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginSuccessPage {

  private static final String FLASH_MSG_TEXT_ID = "flash";

  @FindBy(id = FLASH_MSG_TEXT_ID)
  private WebElement flashMessageTextElement;

  private WebDriver driver;

  public LoginSuccessPage(WebDriver driver) {
    this.driver = driver;
  }

  public String getFlashMessage() {
    return flashMessageTextElement.getText();
  }
}
