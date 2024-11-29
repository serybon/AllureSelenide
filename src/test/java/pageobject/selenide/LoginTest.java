package pageobject.selenide;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic("Authorization and Authentication")
@Feature("UI Login form")
public class LoginTest extends TestBase {

    @Description("This test attempts to login with correct email and incorrect password and validates error message appears.")
    @Test
    public void loginWithCorrectEmailAndWrongPassword() {
        LoginPage.loginWithCredentials("mail5514@mail.com", "111111");
        LoginPage.validateErrorMessageIsDisplayed();
        LoginPage.validateErrorMessageText("Wrong password or the account is disabled, or does not exist");
    }
    @Description("This test attempts to login with correct email and password and validates error message appears.")
    @Test
    public void loginWithCorrectCredentials() {
        LoginPage.loginWithCredentials("mail8164@mail.com", "bB123123");
        LoginPage.validateErrorMessageIsDisplayed();
        LoginPage.validateErrorMessageText("Wrong password or the account is disabled, or does not exist");
    }

    @Description("This test tries to create a new account and validates successful message.")
    @Test
    public void successfulRegistration() {
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
