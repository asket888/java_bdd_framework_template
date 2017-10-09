package pages;

import locators.AuthorizationLocator;
import org.openqa.selenium.By;
import utils.ElementManagementUtil;

public class AuthorizationPage implements AuthorizationLocator {

    private static ElementManagementUtil elementManagement = new ElementManagementUtil();

    public void loginIntoApp(String login, String password) {

        elementManagement
                .setValueIntoInputAfterClear(getAuthorizationLoginInput(), login);
        elementManagement
                .setValueIntoInputAfterClear(getAuthorizationPasswordInput(), password);
        elementManagement
                .clickTheElement(getLoginButton());
    }

    private static By getAuthorizationLoginInput() {
        return By.xpath(AUTHORIZATION_LOGIN_LOCATOR);}

    private static By getAuthorizationPasswordInput() {
        return By.xpath(AUTHORIZATION_PASSWORD_LOCATOR);}

    private static By getLoginButton() {
        return By.xpath(AUTHORIZATION_LOGIN_BUTTON_LOCATOR);}
}
