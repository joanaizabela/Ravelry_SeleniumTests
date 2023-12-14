package pom.pages.ravelry;

import pom.pages.Common;
import pom.pages.Locator;

public class PatternPage {
    public static void openUrl(String url) {
        Common.setUpChrome(false);
        Common.openUrl(url);
    }

    public static void logIn(String username, String password) {
        LoginPage.writeUsername(username);
        LoginPage.writePassword(password);
        LoginPage.clickButtonLogIn();
    }

    public static void goToPattern(String patternUrl) {
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Home.linkFromUserPicture, 10);
        if (isVisible) {
            Common.goToUrl(patternUrl);
        }
    }

    public static void clickButtonSaveInFavorites() {
        Common.clickOnElement(Locator.Ravelry.Pattern.buttonSaveInFavorites);
    }

    public static void clickButtonSaveChanges() {
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Pattern.buttonSaveChanges, 10);
        if (isVisible) {
            Common.clickOnElement(Locator.Ravelry.Pattern.buttonSaveChanges);
        }
    }

    public static void navigateToMyFavorites() {
        Common.hoverOnElement(Locator.Ravelry.Home.linkMyNotebook);
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Home.linkFavoritesFromDropdown, 10);
        if (isVisible) {
            Common.clickOnElement(Locator.Ravelry.Home.linkFavoritesFromDropdown);
        }
    }

    public static String getPatternLink(int index) {
        return Common.getAttribute(Locator.Ravelry.Favorites.linkPatternTitle(index), "href");
    }

    public static void removePatternFromFavorites(int index) {
        Common.hoverOnElement(Locator.Ravelry.Favorites.divPatternPicture(index));
        boolean isDeleteVisible =
                Common.waitForElementToBeVisible(Locator.Ravelry.Favorites.buttonRemoveFavorite(index), 10);
        if (isDeleteVisible) {
            Common.clickOnElement(Locator.Ravelry.Favorites.buttonRemoveFavorite(index));
        }
        boolean isConfirmationVisible =
                Common.waitForElementToBeVisible(Locator.Ravelry.Favorites.buttonRemoveConfirm, 10);
        if (isConfirmationVisible) {
            Common.clickOnElement(Locator.Ravelry.Favorites.buttonRemoveConfirm);
        }
    }

    public static void addNoteWhenSavingFavorite(String text) {
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Pattern.textareaFavoriteNotes, 10);
        if (isVisible) {
            Common.addText(Locator.Ravelry.Pattern.textareaFavoriteNotes, text);
        }
    }

    public static String getPatternNote(int index) {
        try {
            return Common.getText(Locator.Ravelry.Favorites.linkPatternNotes(index));
        } catch (Exception e) {
            return "";
        }
    }
}
