package pom.pages;

import org.openqa.selenium.By;

public class Locator {
    public static class Ravelry {
        public static class Home {
            public static By linkFromUserPicture = By.xpath("//a[@class='navigation_v2__avatar__link']");
            public static By linkYarns =
                    By.xpath("//div[@class='navigation_v2__tab navigation_v2__tab-- navigation_v2__tab--dropdown']/a[@href='/yarns']");
            public static By linkMyNotebook = By.xpath("//div[@id='notebook_tab']");
            public static By linkFavoritesFromDropdown =
                    By.xpath("//div[@id='notebook_tab']//a[contains(@href,'favorites')]");
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
        public static class Projects {
            public static By linkProjectByIndex(int projectIndex) {
                return By.xpath("(//a[@class='notebook_thumbnail__title'])[%d]".formatted(projectIndex));
            }
        }
        public static class Favorites {
            public static By buttonRemoveConfirm =
                    By.xpath("(//a[@class='hover_tool_delete_confirmation__confirm'])");
            public static By linkPatternTitle(int index) {
                return By.xpath("(//li[@class='title'])[%s]/a".formatted(index));
            }
            public static By divPatternPicture(int index) {
                return By.xpath("(//div[@class='photo_frame photo_gallery__frame real_photo'])[%s]".formatted(index));
            }
            public static By buttonRemoveFavorite(int index) {
                return By.xpath("(//a[@class='hover_tool_icon hover_tool_delete hover_tool_icon--icon_only'])[%s]"
                        .formatted(index));
            }
            public static By linkPatternNotes(int index) {
                return By.xpath("(//li[@class='notes'])[%s]".formatted(index));
            }
        }
        public static class Project {
            public static By paragraphNotesEntry = By.xpath("//div[@class='markdown__dated_entry']"); // removed /p at end
            public static By textareaNotesEntry =
                    By.xpath("//textarea[@class='markdown__inline_editor c-markdown_area c-text_editor']");
            public static By buttonSaveThisNote =
                    By.xpath("//button[@class='clicker_v2 clicker_v2--standard ']");
            public static By linkAddNote = By.xpath("//span[contains(text(),'add note')]/..");
            public static By starRatingOverall(int rating) {
                return By.xpath("//ul[contains(@id,'rating')]/li[%d]".formatted(rating));
            }
            public static By starRatingClarity(int rating) {
                return By.xpath("//ul[contains(@id,'clarity')]/li[%d]".formatted(rating));
            }
            public static By barRatingDifficulty(int rating) {
                return By.xpath("//ul[contains(@id,'difficulty')]/li[%d]".formatted(rating));
            }
            public static By barProgress(int segmentIndex) {
                return By.xpath("(//ul[contains(@class,'project_progress rating')])[2]/li[%d]".formatted(segmentIndex));
            }
            public static By divPercentageProgress() {
                return By.xpath("//div[@id='status_view']//div[@class='percentage progress_name']");
            }
        }
        public static class Pattern {
            public static By buttonSaveInFavorites =
                    By.xpath("//a[@class='favorites_button heartpuff button button_false']");
            public static By buttonSaveChanges =
                    By.xpath("//button[@class='clicker_v2 clicker_v2--standard ']");
            public static By textareaFavoriteNotes = By.xpath("//textarea[@name='comments']");
        }
    }
}
