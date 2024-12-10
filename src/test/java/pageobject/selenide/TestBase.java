package pageobject.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

@Listeners({ScreenshotListener.class})//ReportPortalTestNGListener.class
public class TestBase {

    WebDriver driver;

    @BeforeMethod
    public void methodSetup() throws MalformedURLException {
//        String browser = System.getProperty("browser", "chrome");
//        String platform = System.getProperty("os", "windows");
//
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setBrowserName(browser);
//
//        switch (platform) {
//            case "windows" -> caps.setPlatform(Platform.WINDOWS);
//            case "mac" -> caps.setPlatform(Platform.MAC);
//            case "linux" -> caps.setPlatform(Platform.LINUX);
//        }

        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-bnds1988-b9ee5");
        sauceOptions.put("accessKey", "283a4f50-d5e2-48fc-a6c7-f6d8058ab9ac");
        sauceOptions.put("build", "My first build");
        sauceOptions.put("name", "build 10.12.2024");
        sauceOptions.put("screenResolution", "1920x1080");
        browserOptions.setCapability("sauce:options", sauceOptions);

        // начать сессию
        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        driver = new RemoteWebDriver(url, browserOptions);

        //Configuration.remote = "http://localhost:4444/wd/hub";

        Configuration.baseUrl = "https://litecart.stqa.ru/en/";
        Configuration.pageLoadTimeout = 5000;

        WebDriverRunner.setWebDriver(driver);
        open(Configuration.baseUrl);
    }

    @AfterMethod
    public void methodTeardown(ITestResult result) {
        Selenide.closeWebDriver();
    }

}
