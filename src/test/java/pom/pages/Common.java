package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pom.utils.Driver;

import java.time.Duration;
import java.util.List;

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

    public static String getAttribute(By locator, String attribute) {
        return getElement(locator).getAttribute(attribute);
    }

    public static void clickOnElement(By locator) {
        getElement(locator).click();
    }

    public static void addText(By locator, String text) {
        getElement(locator).sendKeys(text);
    }

    public static String getText(By locator) {
        return getElement(locator).getText();
    }

    public static String getText(WebElement element) {
        return element.getText();
    }

    public static boolean waitForElementToBeVisible(By locator, int seconds) {
        boolean isVisible;
        int duration = 0;
        do {
            try {
                Thread.sleep(500);
                getElement(locator).isDisplayed();
                isVisible = true;
            } catch (NoSuchElementException e) {
                isVisible = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            duration++;
        } while (!isVisible || duration / 2 >= seconds);
        return isVisible;
    }

    public static List<WebElement> getSearchResults(By locator) {
        return getElements(locator);
    }

    private static WebElement getElement(By locator) {
        return Driver.getDriver().findElement(locator);
    }

    private static List<WebElement> getElements(By locator) {
        return Driver.getDriver().findElements(locator);
    }
}
