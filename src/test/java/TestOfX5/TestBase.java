package TestOfX5;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import TestOfX5.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import static java.lang.String.format;


public class TestBase  {

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", System.getProperty("browserName", "chrome"));
        capabilities.setCapability("browserVersion", System.getProperty("browserVersion", "99"));
        Configuration.browserCapabilities = capabilities;
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.remote=System.getProperty("remote");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}