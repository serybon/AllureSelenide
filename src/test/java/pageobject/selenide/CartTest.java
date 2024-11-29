package pageobject.selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic("Cart functionality")
@Feature("Cart")
public class CartTest extends TestBase {

    @Description("This test verifies that there are no items in the cart.")
    @Test
    public void checkEmptyCartTest() {
        CartPage.clickCart();
        CartPage.validateEmptyCartMessageText("There are no items in your cart.");
    }

    @Description("This test checks that the cart changes the quantity in the header when the add to cart button is clicked.")
    @Test
    public void addOnePurpleDuckToCart() {
        CartPage.clickPurpleDuckLink();
        CartPage.clickAddToCartButton();
        CartPage.validateAddOnePurpleDuckToCart("1");
    }
}
