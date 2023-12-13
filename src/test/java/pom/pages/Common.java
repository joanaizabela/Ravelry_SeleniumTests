package pom.pages;

import pom.utils.Driver;

import java.time.Duration;

public class Common {
    public static void setUpChrome(boolean isHeadless) {
        Driver.setUpChromeDriver(isHeadless);
    }

    public static void setUpChrome(boolean isHeadless, int waitDurationSeconds) {
        Driver.setUpChromeDriver(isHeadless);
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitDurationSeconds));
    }

    public static void openUrl(String url) {
        Driver.getDriver().get(url);
    }

    public static void closeWindow() {
        Driver.closeDriver();
    }

    public static void quitDriver() {
        Driver.quitDriver();
    }
}
