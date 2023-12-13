package pom.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setUpChromeDriver(boolean isHeadless) {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions option = new ChromeOptions();
        if (isHeadless) {
            option.addArguments("--headless=new");
        }
        option.addArguments("start-maximized");
        option.addArguments("--force-device-scale-factor=0.70");
        driver.set(new ChromeDriver(option));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().close();
    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}
