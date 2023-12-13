package pom.pages;

import org.openqa.selenium.By;

public class Locator {
    public static class Ravelry {
        public static class Home {
            public static By linkFromUserPicture = By.xpath("//a[@class='navigation_v2__avatar__link']");
        }
        public static class Login {
            public static By inputUsername = By.xpath("//input[@id='user_login']");
            public static By inputPassword = By.xpath("//input[@id='user_password']");
            public static By buttonLogIn = By.xpath("//button[@type='submit']");
            public static By divLoginError = By.xpath("//div[@class='splash_page__error']");
        }
    }
}
