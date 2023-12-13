package pom.tests.ravelry;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.tests.TestBase;
import pom.pages.ravelry.YarnsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YarnsTest extends TestBase {
    @BeforeMethod
    @Override
    public void setUp() {
        YarnsPage.openUrl("https://www.ravelry.com/account/login");
        YarnsPage.logIn("vcstestuser", "-vcs-_TestPa$$#2#2");
        YarnsPage.openYarnsPage();
    }

    @DataProvider (name = "browseYarnsByFiber")
    public Object[][] provideDataForYarnSearch() {
        List<String> cottonSubtypes = Arrays.asList("Acala", "Egyptian", "Pima");
        List<String> woolSubtypes = Arrays.asList("Merino", "Icelandic", "Shetland", "Corriedale", "Polwarth", "Bluefaced");
        List<String> mohairSubtypes = new ArrayList<>();
        List<String> rayonSubtypes = new ArrayList<>();

        return new Object[][] {
                {"Cotton", cottonSubtypes},
                {"Wool", woolSubtypes},
                {"Mohair", mohairSubtypes},
                {"Rayon", rayonSubtypes}
        };
    }
    @Test (dataProvider = "browseYarnsByFiber")
    public void ts2_browseYarnsByFiber(String fiberType, List<String> fiberSubtypes) {
        List<String> fiberTypesToSearch = new ArrayList<>();
        fiberTypesToSearch.add(fiberType);
        fiberTypesToSearch.addAll(fiberSubtypes);
        boolean actualResult;

        YarnsPage.clickFiberType(fiberType);
        actualResult = YarnsPage.checkIfSearchResultsAllContainString("details", fiberTypesToSearch);

        Assert.assertTrue(actualResult);
    }

    @DataProvider (name = "searchYarnsUsingQuery")
    public Object[][] provideDataForYarnSearchUsingQuery() {
        return new Object[][] {
                {"baby", true, true},
                {" soft  ", true, true},
                {"SiLK", true, true},
                {"nonExistingQuery0123456", false, false},
                {"$%#specialQuery%-^", false, false}
        };
    }
    @Test (dataProvider = "searchYarnsUsingQuery")
    public void ts2_searchYarnsUsingQuery(String searchQuery, boolean returnsResults, boolean resultsAreCorrect) {
        boolean doesSearchReturnResults;
        boolean actualResult = false;

        YarnsPage.writeSearchQuery(searchQuery);
        YarnsPage.clickButtonSearchYarns();

        doesSearchReturnResults = YarnsPage.checkIfSearchReturnedResults();
        if (doesSearchReturnResults) {
            actualResult = YarnsPage.checkIfSearchResultsAllContainString(searchQuery.trim().toLowerCase());
        }

        Assert.assertTrue(
                (doesSearchReturnResults == returnsResults) && (actualResult == resultsAreCorrect)
        );
    }

    @Test
    public void ts2_searchYarnsUsingBlankQuery_testIfResultsSortedByProjectCount() {
        boolean actualResult;

        YarnsPage.clickButtonSearchYarns();

        actualResult = YarnsPage.checkIfSearchResultsAreSorted("projects", "descending");

        Assert.assertTrue(actualResult);
    }
}
