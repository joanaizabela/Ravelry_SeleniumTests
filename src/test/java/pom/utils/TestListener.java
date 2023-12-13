package pom.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private void takeScreenshot() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) Driver.getDriver();
            File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss_SSS");
            String dateTime = LocalDateTime.now().format(formatter);

            String fileDirectory = "./screenshots/" + dateTime + "_screenshot.png";

            File file = new File(fileDirectory);
            FileUtils.copyFile(screenshotFile, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot();
    }
}
