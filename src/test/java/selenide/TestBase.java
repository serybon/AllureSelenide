package selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pageobject.ScreenshotListener;
import pageobject.enums.Browser;
import pageobject.enums.OperatingSystem;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.open;

@Listeners({ScreenshotListener.class})//ReportPortalTestNGListener.class
public class TestBase {

    @BeforeMethod
    public void methodSetup() throws MalformedURLException {

        String url = "https://litecart.stqa.ru/en/";

        DesiredCapabilities caps = new DesiredCapabilities();
        Browser browser = Browser.valueOf(System.getProperty("browser", "chrome"));
        OperatingSystem os = OperatingSystem.valueOf(System.getProperty("os", "windows"));

        switch (browser) {
            case chrome -> caps.setBrowserName("chrome");
            case firefox -> caps.setBrowserName("firefox");
            default -> caps.setBrowserName("chrome");
        }

        switch (os) {
            case windows -> caps.setPlatform(Platform.WINDOWS);
            case linux -> caps.setPlatform(Platform.LINUX);
            default -> caps.setPlatform(Platform.WINDOWS);
        }

        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.77:4444/wd/hub"),caps);

        WebDriverRunner.setWebDriver(driver);
        open(url);
    }

    @AfterMethod
    public void methodTeardown(ITestResult result) {
        Selenide.closeWebDriver();
    }

}
