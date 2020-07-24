package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    @Description("Login process finished successful")
    public void loginWithValidData() {
        loginPage
                .openPage()
                .isPageOpened()
                .login("kristina@mailinator.com", "password123")
                .verifyLoginSuccess();
    }

    @Test
    @Description("Verifying notification message is present")
    public void loginWithEmptyFields() {
        loginPage
                .openPage()
                .isPageOpened()
                .login("", "")
                .verifyErrorMessageIsDisplayed("Missing credentials");
    }

    @Test
    @Description("Verifying error message is present")
    public void loginWithIncorrectPassword() {
        loginPage
                .openPage()
                .isPageOpened()
                .login("kristina@mailinator.com", "123")
                .verifyErrorMessageIsDisplayed("Error: Password is incorrect");
    }
}

