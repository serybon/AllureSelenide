package pageobject.selenide;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {


    private static By purpleDuckLink = By.cssSelector("[alt='Purple Duck']");
    private static By addToCartButton = By.cssSelector("[name='add_cart_product'][type='submit']");
    private static By cartQuantityLabelLocator = By.cssSelector("span[class='quantity']");
    //private static By cartLink = By.cssSelector("#cart a[class='content']");
    //private static By emptyCartMessage = By.cssSelector("#checkout-cart-wrapper > p > em");


    @Step("Click on the Purple Duck element with the link.")
    public static void clickPurpleDuckLink() {
        $(purpleDuckLink).click();
    }

    @Step("Click on the add to cart button.")
    public static void clickAddToCartButton() {
        $(addToCartButton).click();
    }

    @Step("Validates that quantity matches expectation.")
    public static void validateAddOnePurpleDuckToCart(String expectedText) {
        $(cartQuantityLabelLocator).shouldHave(text(expectedText));
    }

    @Step("click on cart to enter it.")
    public static void clickCart() {
        $("#cart a[class='content']").click();
    }

    @Step("Validates that empty cart message matches expectation")
    public static void validateEmptyCartMessageText(String expectedText) {
        $("#checkout-cart-wrapper > p > em").shouldHave(text(expectedText));
    }
}
