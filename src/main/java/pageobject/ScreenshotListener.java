package pageobject;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;

import static com.codeborne.selenide.Selenide.screenshot;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        //All variants work when testng version 7.4.0 and older
        //Variant 1 Vadim
        File screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);

        try {
            Allure.addAttachment("screenshot" + result.getMethod().getMethodName(), "image/png", new FileInputStream(screenshot), "png");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't take screenshot\n" + e.getMessage());
        }

        //ReportPortal.emitLog("Failure description listener", LogLevel.INFO.name(), Calendar.getInstance().getTime(), screenshot);



//        //Variant 2 internet
//        attachScreenshot("screenshot_" + result.getMethod().getMethodName() + "_failure");
//        //Variant 3 internet
//        takeScreenshot("It my screenshot_" + result.getMethod().getMethodName());
//        //Variant 4 internet
//        screenshot1("ho-ho-ho");
    }

    public static void screenshot1(String name) {
        byte[] screenshot = Selenide.screenshot(OutputType.BYTES);

        try (InputStream is = new ByteArrayInputStream(screenshot)) {
            Allure.attachment("imageTestFailure.png", is);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    public byte[] takeScreenshot(String name) {
        File screenshot = Screenshots.takeScreenShotAsFile();
        if (screenshot != null) {
            try {
                return Files.toByteArray(screenshot);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Screenshot is null!");
        return new byte[0];
    }


    //РАБОТАЕТ
    @Step("Attach Screenshot")
    @Attachment(value = "{name}", type = "image/png")
    public static byte[] attachScreenshot(String name) {
        // Делаем скриншот с Selenide и получаем его как массив байтов
        return screenshot(OutputType.BYTES);
    }


}
