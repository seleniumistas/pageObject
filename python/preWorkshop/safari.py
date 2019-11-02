from selenium import webdriver
import unittest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions

class MyFirstTests(unittest.TestCase):

    SITE_URL = "http://the-internet.herokuapp.com/login"
    USERNAME_INPUT_ID = "username"
    PASSWORD_INPUT_ID = "password"
    LOGIN_BUTTON_CSS = "button[type='submit']"
    FLASH_MSG_TEXT_ID = "flash"
    TEN_SECONDS = 10

    def setUp(self):
        # Initializes the driver from standard location
        self.driver = webdriver.Firefox()

        ## To work with other browsers:
        # self.driver = webdriver.Chrome()
        # self.driver = webdriver.Safari()
        ##

        self.driver.get(self.SITE_URL)
        self.pause = WebDriverWait(self.driver, self.TEN_SECONDS)

    def tearDown(self):
        self.driver.quit()
    
    def test_login(self):
        username = "tomsmith"
        password = "SuperSecretPassword!"
        driver = self.driver

        # Go to the test site
        
        # Find username input field and enter username
        usernameInputElement = driver.find_element_by_id(self.USERNAME_INPUT_ID)
        usernameInputElement.send_keys(username)
        
        # Find password input filed and enter password
        passwordInputElement = driver.find_element_by_id(self.PASSWORD_INPUT_ID)
        passwordInputElement.send_keys(password)

        # Find Login button and click it
        loginButtonElement = driver.find_element_by_css_selector(self.LOGIN_BUTTON_CSS)
        loginButtonElement.click()

        ## If you're working with Safari, click() doesn't work, so replace line above by this:
        #
        # loginButtonElement.send_keys(Keys.RETURN)
        #
        ## You will need to import Keys with the following line at the top:
        #
        # from selenium.webdriver.common.keys import Keys
        ##

        # Find flash test and get text
        flashText = self.pause.until(
            expected_conditions.visibility_of_element_located((By.ID, self.FLASH_MSG_TEXT_ID))
            ).text
        self.assertTrue(
            "You logged into a secure area!" in flashText,
            "Message is incorrect, should contain \"You logged into a secure area!\""
            )

if __name__ == '__main__':
    unittest.main()
