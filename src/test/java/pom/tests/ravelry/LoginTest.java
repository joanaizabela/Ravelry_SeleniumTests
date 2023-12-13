package pom.tests.ravelry;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.pages.ravelry.LoginPage;
import pom.tests.TestBase;

public class LoginTest extends TestBase {
    @BeforeMethod
    @Override
    public void setUp() {
        LoginPage.openUrl("https://www.ravelry.com/account/login");
    }

    @Test (priority = 1)
    public void ts1_testLogin_providingCorrectData() {
        String username = "vcstestuser";
        String password = "-vcs-_TestPa$$#2#2";
        String actualResult;

        LoginPage.writeUsername(username);
        LoginPage.writePassword(password);
        LoginPage.clickButtonLogIn();

        actualResult = LoginPage.readUsernameFromProfilePictureLink();

        Assert.assertEquals(actualResult, username.toLowerCase());
    }

    @DataProvider (name = "loginPage")
    public Object[][] provideDataForLogin() {
        return new Object[][] {
                {"", "", "username"},
                {"", "correctPassword", "username"},
                {"nonExistingUser7g4g4g4g5f", "correctPassword", "username"},
                {"nonExistingUser6b1s1s1s2d", "", "username"},
                {"vcstestuser", "", "password"},
                {"vcstestuser", "incorrectpassword", "password"}
        };
    }
    @Test (dataProvider = "loginPage", threadPoolSize = 6, priority = 2)
    public void ts1_testLogin_providingIncorrectData(String username, String password, String errorField) {
        String expectedResult = "Your %s is incorrect".formatted(errorField);
        String actualResult;

        LoginPage.writeUsername(username);
        LoginPage.writePassword(password);
        LoginPage.clickButtonLogIn();
        actualResult = LoginPage.readLoginError();

        Assert.assertEquals(actualResult, expectedResult);

    }


}
