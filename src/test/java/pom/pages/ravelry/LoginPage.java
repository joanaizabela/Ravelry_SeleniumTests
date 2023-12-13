package pom.pages.ravelry;

import pom.pages.Common;
import pom.pages.Locator;

public class LoginPage {
    public static void openUrl(String url) {
        Common.setUpChrome(false, 10);
        Common.openUrl(url);
    }

    public static void writeUsername(String username) {
        Common.addText(Locator.Ravelry.Login.inputUsername, username);
    }

    public static void writePassword(String password) {
        Common.addText(Locator.Ravelry.Login.inputPassword, password);
    }

    public static void clickButtonLogIn() {
        Common.clickOnElement(Locator.Ravelry.Login.buttonLogIn);
    }

    public static String readUsernameFromProfilePictureLink() {
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Home.linkFromUserPicture, 10);
        if (isVisible) {
            String userLink = Common.getAttribute(Locator.Ravelry.Home.linkFromUserPicture, "href");
            return userLink.toLowerCase().substring(31);
        } else {
            return "Unable to read username from profile picture link...";
        }
    }

    public static String readLoginError() {
        return Common.getText(Locator.Ravelry.Login.divLoginError);
    }
}
