package pageobject.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Browsers.*;
import static com.codeborne.selenide.Selenide.open;

//@Listeners(ScreenshotListener.class)
public class TestBase {

    String baseUrl = "https://litecart.stqa.ru/en/";

    @BeforeMethod
    public void methodSetup() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.baseUrl = "https://litecart.stqa.ru/en/";
        Configuration.pageLoadTimeout = 5000;
        open("https://litecart.stqa.ru/en/");
    }

    @AfterMethod
    public void methodTeardown() {
        Selenide.closeWebDriver();
    }

}
