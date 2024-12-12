package pageobject.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
//import com.epam.reportportal.service.ReportPortal;
//import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.LogLevel;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Browsers.*;
import static com.codeborne.selenide.Selenide.open;

@Listeners({ScreenshotListener.class})//ReportPortalTestNGListener.class
public class TestBase {

    @BeforeMethod
    public void methodSetup() {
        String browser = System.getProperty("browser", "chrome");
        String platform = System.getProperty("os", "windows");
        WebDriver driverFirefox = new FirefoxDriver();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);

        switch (platform){
            case "windows": caps.setPlatform(Platform.WINDOWS);
            case "mac": caps.setPlatform(Platform.MAC);
            case "linux": caps.setPlatform(Platform.LINUX);
        }

        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.baseUrl = "https://litecart.stqa.ru/en/";
        Configuration.pageLoadTimeout = 5000;
        open(Configuration.baseUrl);
    }

    @AfterMethod
    public void methodTeardown(ITestResult result) {
        Selenide.closeWebDriver();
    }

}
