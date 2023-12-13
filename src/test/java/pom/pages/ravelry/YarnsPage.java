package pom.pages.ravelry;

import org.openqa.selenium.WebElement;
import pom.pages.Common;
import pom.pages.Locator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class YarnsPage {
    public static void openUrl(String url) {
        Common.setUpChrome(true, 10);
        Common.openUrl(url);
    }

    public static void logIn(String username, String password) {
        LoginPage.writeUsername(username);
        LoginPage.writePassword(password);
        LoginPage.clickButtonLogIn();
    }

    public static void openYarnsPage() {
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Home.linkYarns, 10);
        if (isVisible) {
            Common.clickOnElement(Locator.Ravelry.Home.linkYarns);
        }
    }

    public static void clickFiberType(String fiberType) {
        Common.clickOnElement(Locator.Ravelry.Yarn.linkFiberType(fiberType));
    }

    public static boolean checkIfSearchResultsAllContainString(String className, List<String> targetStrings) {
        List<WebElement> yarnSearchResultsDetails =
                Common.getSearchResults(Locator.Ravelry.Yarn.divYarnSearchResultsDescriptions(className));

        for (WebElement detail : yarnSearchResultsDetails
            ) {
                String asText = Common.getText(detail);
                boolean containsFilter = false;
                for (String targetString : targetStrings
                ) {
                    if (asText.contains(targetString)){
                        containsFilter = true;
                        break;
                    }
                }
                if (!containsFilter){
                    return false;
                }
            }
        return true;
    }

    public static boolean checkIfSearchResultsAllContainString(String targetString) {
        List<WebElement> allSearchResults = Common.getSearchResults(Locator.Ravelry.Yarn.divYarnSearchAllResults);

        for (int i = 0; i < allSearchResults.size(); i++) {
            String title =
                    Common.getText(Locator.Ravelry.Yarn.divYarnSearchResults(
                            "span/a", i * 2 + 1
                    )).trim().toLowerCase();
            String manufacturer =
                    Common.getText(Locator.Ravelry.Yarn.divYarnSearchResults(
                            "div[@class='yarn_company']", i + 1
                    )).trim().toLowerCase();
            String description =
                    Common.getText(Locator.Ravelry.Yarn.divYarnSearchResults(
                            "div[@class='details']", i + 1
                    )).trim().toLowerCase();

            if (!title.contains(targetString) && !manufacturer.contains(targetString) && !description.contains(targetString)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfSearchReturnedResults() {
        List<WebElement> allSearchResults = Common.getSearchResults(Locator.Ravelry.Yarn.divYarnSearchAllResults);
        return !allSearchResults.isEmpty();
    }

    public static void writeSearchQuery(String searchQuery) {
        boolean isVisible = Common.waitForElementToBeVisible(Locator.Ravelry.Yarn.inputSearchYarns, 10);
        if (isVisible) {
            Common.addText(Locator.Ravelry.Yarn.inputSearchYarns, searchQuery);
        }
    }

    public static void clickButtonSearchYarns() {
        Common.clickOnElement(Locator.Ravelry.Yarn.buttonSearchYarns);
    }

    public static boolean checkIfSearchResultsAreSorted(String className, String sortingOrder) {
        List<WebElement> yarnSearchResultsProjects =
                Common.getSearchResults(Locator.Ravelry.Yarn.divYarnSearchResultsDescriptions(className));

        List<Integer> resultIntegers = new ArrayList<>();
        for (WebElement result : yarnSearchResultsProjects
             ) {
            resultIntegers.add(Integer.parseInt(Common.getText(result).replace(" projects", "")));
        }

        List<Integer> comparisonList = new ArrayList<>(resultIntegers);
        switch (sortingOrder.toLowerCase()) {
            case "descending":
                comparisonList.sort(Comparator.reverseOrder());
                break;
            case "ascending":
                comparisonList.sort(Comparator.naturalOrder());
                break;
            default:
                break;
        }
        return comparisonList.equals(resultIntegers);
    }
}
