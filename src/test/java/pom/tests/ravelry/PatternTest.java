package pom.tests.ravelry;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.pages.ravelry.PatternPage;
import pom.tests.TestBase;

public class PatternTest extends TestBase {
    public static String patternUrl = "https://www.ravelry.com/patterns/library/champagne-cardigan";

    @BeforeMethod
    @Override
    public void setUp() {
        PatternPage.openUrl("https://www.ravelry.com/account/login");
        PatternPage.logIn("vcstestuser", "-vcs-_TestPa$$#2#2");
        PatternPage.goToPattern(patternUrl);
    }

    @AfterMethod
    public void removeTestedPatternFromFavorites() {
        PatternPage.removePatternFromFavorites(1);
    }

    @Test (priority = 1)
    public void ts7_addPatternToFavoritesWithoutAddingNote() {
        String expectedUrl = patternUrl;
        String actualUrl;

        PatternPage.clickButtonSaveInFavorites();
        PatternPage.clickButtonSaveChanges();

        PatternPage.navigateToMyFavorites();

        actualUrl = PatternPage.getPatternLink(1);

        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test (priority = 2)
    public void ts7_addPatternToFavoritesAndAddNote() {
        String textToAdd = "Some text for favorite pattern.";
        String expectedDescription = textToAdd;
        String actualDescription;

        PatternPage.clickButtonSaveInFavorites();
        PatternPage.addNoteWhenSavingFavorite(textToAdd);
        PatternPage.clickButtonSaveChanges();

        PatternPage.navigateToMyFavorites();

        actualDescription = PatternPage.getPatternNote(1);

        Assert.assertEquals(actualDescription, expectedDescription);
    }
}
