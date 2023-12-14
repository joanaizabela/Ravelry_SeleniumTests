package pom.tests.ravelry;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.pages.ravelry.ProjectPage;
import pom.tests.TestBase;

public class ProjectTest extends TestBase {
    @BeforeMethod
    @Override
    public void setUp() {
        ProjectPage.openUrl("https://www.ravelry.com/account/login");
        ProjectPage.logIn("vcstestuser", "-vcs-_TestPa$$#2#2");
        ProjectPage.openProjectPage(1);
    }

    @DataProvider(name = "projectPageNotes")
    public Object[][] provideDataForProjectNotes() {
        return new Object[][] {
                {" This is text to append.", false},
                {"\nThis is more text, from a new line this time!\nAnd some more.", false},
                {"This is text that will replace the entire note.", true},
                {"", true},
                {"This text will replace an empty field.", true},
                {"", true},
                {"This text will be added to an empty field!", false},
                {"", false},
                {"This is the starting text in the Notes field of Project 1.\n(Sophie Scarf)", true}
        };
    }
    @Test (dataProvider = "projectPageNotes")
    public void ts4_addOrEditProjectNotes(String textToAdd, boolean replaceExistingNote) {
        String expectedResult;
        String actualResult;

        String startingText = ProjectPage.readNotesParagraph();

        ProjectPage.addOrEditNote();
        if (replaceExistingNote) {
            ProjectPage.removeExistingTextFromNote();
        }
        ProjectPage.addTextToNote(textToAdd);
        ProjectPage.clickButtonSaveThisNote();

        ProjectPage.refreshPage();

        expectedResult = replaceExistingNote ? textToAdd : startingText.concat(textToAdd);
        actualResult = ProjectPage.readNotesParagraph();

        System.out.println("Expected:\n" + expectedResult);
        System.out.println("Actual:\n" + actualResult);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "projectPageRating")
    public Object[][] provideDataForProjectRating() {
        return new Object[][] {
                {0, 0, 1},
                {1, 0, 10},
                {0, 0, 0},
                {5, 4, 6}
        };
    }
    @Test (dataProvider = "projectPageRating")
    public void ts5_changePatternRatingFromProjectPage(int overallRating, int clarityRating, int difficultyRating) {
        // here if any one of the values provided is 0, no action is taken for that rating type
        // (to reduce the number of tests and log-ins)
        int expectedOverallRating;
        int expectedClarityRating;
        int expectedDifficultyRating;

        if (overallRating != 0) {
            expectedOverallRating = overallRating;
            ProjectPage.clickRateOverall(overallRating);
        } else {
            expectedOverallRating = ProjectPage.readOverallRatingValue();
        }
        if (clarityRating != 0) {
            expectedClarityRating = clarityRating;
            ProjectPage.clickRateClarity(clarityRating);
        } else {
            expectedClarityRating = ProjectPage.readClarityRatingValue();
        }
        if (difficultyRating != 0) {
            expectedDifficultyRating = difficultyRating;
            ProjectPage.clickRateDifficulty(difficultyRating);
        } else {
            expectedDifficultyRating = ProjectPage.readDifficultyRatingValue();
        }

        ProjectPage.refreshPage();

        int starOverallRatingAfter = ProjectPage.readOverallRatingValue();
        int starClarityRatingAfter = ProjectPage.readClarityRatingValue();
        int barDifficultyRatingAfter = ProjectPage.readDifficultyRatingValue();

        Assert.assertTrue(
                starOverallRatingAfter == expectedOverallRating
                && starClarityRatingAfter == expectedClarityRating
                && barDifficultyRatingAfter == expectedDifficultyRating
        );
    }

    @DataProvider(name = "projectPageProgress")
    public Object[][] provideDataForProjectProgress() {
        return new Object[][] {
                {1, 0},
                {21, 99},
                {22, 100},
                {13, 60},
                {20, 95}
        };
    }
    @Test (dataProvider = "projectPageProgress")
    public void ts6_changeProgressOfProject(int segmentNumber, int expectedPercentageValue) {
        int actualPercentageValue;

        ProjectPage.clickBarProgress(segmentNumber);
        ProjectPage.refreshPage();

        actualPercentageValue = ProjectPage.readProgressPercentage();

        Assert.assertEquals(actualPercentageValue, expectedPercentageValue);
    }

}
