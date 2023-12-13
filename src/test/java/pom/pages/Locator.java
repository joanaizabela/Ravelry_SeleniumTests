package pom.pages;

import org.openqa.selenium.By;

public class Locator {
    public static class Ravelry {
        public static class Home {
            public static By linkFromUserPicture = By.xpath("//a[@class='navigation_v2__avatar__link']");
            public static By linkYarns =
                    By.xpath("//div[@class='navigation_v2__tab navigation_v2__tab-- navigation_v2__tab--dropdown']/a[@href='/yarns']");
        }
        public static class Login {
            public static By inputUsername = By.xpath("//input[@id='user_login']");
            public static By inputPassword = By.xpath("//input[@id='user_password']");
            public static By buttonLogIn = By.xpath("//button[@type='submit']");
            public static By divLoginError = By.xpath("//div[@class='splash_page__error']");
        }
        public static class Yarn {
            public static By inputSearchYarns =
                    By.xpath("//div[@class='rsp_hidden yarns_landing__search']//input[@id='query']");
            public static By buttonSearchYarns =
                    By.xpath("//div[@class='rsp_hidden yarns_landing__search']//input[@type='submit']");
            public static By linkFiberType(String fiberType) {
                return By.xpath("//a[@href='/yarns/library/%s/browse']".formatted(fiberType.toLowerCase()));
            }
            public static By divYarnSearchAllResults =
                    By.xpath("//div[@class='advanced_search__items advanced_search__items--yarn_search_result_thumblist']/div");
            public static By divYarnSearchResults(String elementName, int index) {
                return By.xpath(
                        "(//div[@class='advanced_search__items advanced_search__items--yarn_search_result_thumblist']/div/div/%s)[%d]"
                                .formatted(elementName, index));
            }
            public static By divYarnSearchResultsDescriptions(String className) {
                return By.xpath(
                        "//div[@class='advanced_search__items advanced_search__items--yarn_search_result_thumblist']/div/div/div[@class='%s']"
                                .formatted(className.toLowerCase())
                );
            }
        }
    }
}
