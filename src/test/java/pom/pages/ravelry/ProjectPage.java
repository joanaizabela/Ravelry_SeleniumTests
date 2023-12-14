package pom.pages.ravelry;

import org.openqa.selenium.NoSuchElementException;
import pom.pages.Common;
import pom.pages.Locator;

public class ProjectPage {
    public static void openUrl(String url) {
        Common.setUpChrome(true);
        Common.openUrl(url);
    }

    public static void logIn(String username, String password) {
        LoginPage.writeUsername(username);
        LoginPage.writePassword(password);
        LoginPage.clickButtonLogIn();
    }

    public static void openProjectPage(int projectIndex) {
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Home.linkMyNotebook, 10);
        if (isVisible) {
            Common.clickOnElement(Locator.Ravelry.Home.linkMyNotebook);
        }
        boolean projectsAreVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Projects.linkProjectByIndex(1), 10);
        if (projectsAreVisible) {
            Common.clickOnElement(Locator.Ravelry.Projects.linkProjectByIndex(projectIndex));
        }
    }

    public static String readNotesParagraph() {
        try {
            return Common.getText(Locator.Ravelry.Project.paragraphNotesEntry);
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public static void addOrEditNote() {
        try {
            Common.clickOnElement(Locator.Ravelry.Project.paragraphNotesEntry);
        } catch (NoSuchElementException e) {
            clickOnAddNote();
        }
    }

    private static void clickOnAddNote() {
        Common.clickOnElement(Locator.Ravelry.Project.linkAddNote);
    }

    public static void removeExistingTextFromNote() {
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Project.textareaNotesEntry, 10);
        if (isVisible) {
            Common.clearText(Locator.Ravelry.Project.textareaNotesEntry);
        }
    }

    public static void addTextToNote(String text) {
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Project.textareaNotesEntry, 10);
        if (isVisible) {
            Common.addText(Locator.Ravelry.Project.textareaNotesEntry, text);
        }
    }

    public static void clickButtonSaveThisNote() {
        Common.clickOnElement(Locator.Ravelry.Project.buttonSaveThisNote);
    }

    public static void refreshPage() {
        Common.refreshPage();
    }

    public static int readOverallRatingValue() {
        int starRating = 0;

        for (int i = 1; i <= 5; i++) {
            String star = Common.getAttribute(Locator.Ravelry.Project.starRatingOverall(i), "class");
            if (star.equalsIgnoreCase("filled")) {
                starRating++;
            }
        }
        return starRating;
    }

    public static int readClarityRatingValue() {
        int starRating = 0;

        for (int i = 1; i <= 5; i++) {
            String star = Common.getAttribute(Locator.Ravelry.Project.starRatingClarity(i), "class");
            if (star.equalsIgnoreCase("filled")) {
                starRating++;
            }
        }
        return starRating;
    }

    public static int readDifficultyRatingValue() {
        int barRating = 0;

        for (int i = 1; i <= 10; i++) {
            String star = Common.getAttribute(Locator.Ravelry.Project.barRatingDifficulty(i), "class");
            if (star.equalsIgnoreCase("filled")) {
                barRating++;
            }
        }
        return barRating;
    }

    public static void clickRateOverall(int rating) {
        Common.clickOnElement(Locator.Ravelry.Project.starRatingOverall(rating));
    }

    public static void clickRateClarity(int rating) {
        Common.clickOnElement(Locator.Ravelry.Project.starRatingClarity(rating));
    }

    public static void clickRateDifficulty(int rating) {
        Common.clickOnElement(Locator.Ravelry.Project.barRatingDifficulty(rating));
    }

    public static int readProgressPercentage() {
        String textPercentage = Common.getText(Locator.Ravelry.Project.divPercentageProgress()).trim();
        return Integer.parseInt(textPercentage.replace("%", ""));
    }

    public static void clickBarProgress(int progressValue) {
        Common.clickOnElement(Locator.Ravelry.Project.barProgress(progressValue));
    }
}
