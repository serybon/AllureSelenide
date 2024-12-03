package pageobject.selenide;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Authorization and Authentication")
@Feature("UI Login form")
public class LoginTest extends TestBase {

    @Severity(SeverityLevel.BLOCKER)
    @Description("This test attempts to login with correct email and incorrect password and validates error message appears.")
    @Test(priority = 0)
    public void loginWithCorrectEmailAndWrongPassword() {
        LoginPage.loginWithCredentials("mail5514@mail.com", "111111");
        LoginPage.validateErrorMessageIsDisplayed();
        LoginPage.validateErrorMessageText("Wrong password or the account is disabled, or does not exist");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This test attempts to login with correct email and password and validates error message appears.")
    @Test(priority = 1)
    public void loginWithCorrectCredentials() throws IOException {
        LoginPage.loginWithCredentials("mail8164@mail.com", "bb123123");
        LoginPage.validateSuccessMessageIsDisplayed();
        LoginPage.validateSuccessMessageText("You are now logged in as John Johnson.");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This test tries to create a new account and validates successful message.")
    @Test(priority = 5)
    public void successfulRegistration() throws IOException {
        LoginPage.clickLinkForNewCustomers();
        LoginPage.inputDataInAllFields("Vasil", "Vasilevich", "Nemiga street 6-5",
                "220020", "Minsk", LoginPage.generateEmail(), "+123456789", "b123123",
                "b123123");
        LoginPage.selectCountryDropdown("Belarus");
        LoginPage.clickCreateAccountButton();
        LoginPage.validateSuccessMessageIsDisplayed();
        LoginPage.validateSuccessMessageText("Your customer account has been created.");

    }

}
