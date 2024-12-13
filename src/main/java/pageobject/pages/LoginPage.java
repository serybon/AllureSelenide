package pageobject.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static By emailInput = By.name("email");
    private static By passwordInput = By.name("password");
    private static By loginButton = By.name("login");
    private static By errorMessageLabel = By.cssSelector(".notice.errors");
    private static By successMessageLabel = By.cssSelector(".notice.success");

    //Registration locators
    private static By linkForNewCustomers = By.cssSelector("#box-account-login " + "a[href = 'https://litecart.stqa.ru/en/create_account']");
    private static By firstNameInput = By.name("firstname");
    private static By lastNameInput = By.name("lastname");
    private static By addressInput = By.name("address1");
    private static By postcodeInput = By.name("postcode");
    private static By cityInput = By.name("city");
    private static By selectCountryDropdown = By.name("country_code");
    private static By emailRegInput = By.cssSelector("input[type='email'][name='email']");
    private static By phoneInput = By.name("phone");
    private static By passwordRegInput = By.cssSelector("input[type='password'][name='password']");
    private static By confirmedPasswordInput = By.name("confirmed_password");
    private static By createAccountButton = By.name("create_account");
    private static By newsLetterCheckbox = By.name("newsletter");
    //

    //Registration methods
    @Step("Enters data into all input fields to register a new account.")
    public static void inputDataInAllFields(String firstName, String lastName, String address, String postcode, String city, String email, String phone, String password, String password_confirmed) {
        $(firstNameInput).sendKeys(firstName);
        $(lastNameInput).sendKeys(lastName);
        $(addressInput).sendKeys(address);
        $(postcodeInput).sendKeys(postcode);
        $(cityInput).sendKeys(city);
        $(emailRegInput).sendKeys(email);
        $(phoneInput).sendKeys(phone);
        $(passwordRegInput).sendKeys(password);
        $(confirmedPasswordInput).sendKeys(password_confirmed);
        $(newsLetterCheckbox).click();
    }

    @Step("Selects a country from the drop-down list.")
    public static void selectCountryDropdown(String country) {
        $(selectCountryDropdown).selectOption(country);
    }

    @Step("Enter the data in the input field for email.")
    public static void typeEmail(String email) {
        $(emailInput).sendKeys(email);
    }

    @Step("Enter the data in the input field for password.")
    public static void typePassword(String password) {
        $(passwordInput).sendKeys(password);
    }

    @Step("Validates that error message is displayed.")
    public static void validateErrorMessageIsDisplayed() {
        $(errorMessageLabel).shouldBe(Condition.visible);
    }

    @Step("Validates that expected error message text matches expectation")
    public static void validateErrorMessageText(String expectedText) {
        $(errorMessageLabel).shouldHave(text(expectedText));
    }

    @Step("Validates that success message is displayed.")
    public static void validateSuccessMessageIsDisplayed() {
        $(successMessageLabel).shouldBe(Condition.visible);
    }

    @Step("Validates that success message text matches expectation")
    public static void validateSuccessMessageText(String expectedText) {
        $(successMessageLabel).shouldHave(text(expectedText));
    }

    @Step("Click login button.")
    public static void clickLoginButton() {
        $(loginButton).click();
    }

    @Step("Clicks on the link for new customers.")
    public static void clickLinkForNewCustomers() {
        $(linkForNewCustomers).click();
    }

    @Step("Clicks on the link to create new account.")
    public static void clickCreateAccountButton() {
        $(createAccountButton).click();
    }

    @Step("Trying to log in with username and password.")
    public static void loginWithCredentials(String email, String password) {
        LoginPage.typeEmail(email);
        LoginPage.typePassword(password);
        LoginPage.clickLoginButton();
    }

    @Step("Write the generated email and password to a file on disk.")
    public static void WriteToFile(String email, String password) {
        try {
            FileWriter fileWriter = new FileWriter("E:\\IntelliJ_source\\registrationCredentials.txt", true);
            fileWriter.append("\n Email: " + email + " Password: " + password);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, can't write to file. Full error message: " + e.getMessage());
        }
    }

    @Step("Generate new random email.")
    public static String generateEmail() {
        String randomNumber = String.valueOf((int) (Math.floor(Math.random() * 10000)));
        String newEmail = "mail" + randomNumber + "@mail.com";
        WriteToFile(newEmail, "b123123");
        return newEmail;
    }

    @Step("Receive the entered e-mail address in the field.")
    public static String getEnteredEmail() {
        System.out.println($(emailRegInput).getText() + "ПОЧТА");
        return $(emailRegInput).toString();
    }

}
