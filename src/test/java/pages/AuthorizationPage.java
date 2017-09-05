package pages;

import locators.AuthorizationLocator;
import org.openqa.selenium.By;
import steps.setup.BaseTest;
import utils.ElementManagementUtil;

public class AuthorizationPage extends BaseTest implements AuthorizationLocator {

    private static ElementManagementUtil elementManagementUtil = new ElementManagementUtil();

    public void loginIntoAmc(String login, String password) {

        elementManagementUtil
                .setSpecificValueIntoInput(getAuthorizationLoginInput(), login);
        elementManagementUtil
                .setSpecificValueIntoInput(getAuthorizationPasswordInput(), password);
        elementManagementUtil
                .clickButton(getLoginButton());
    }

    private static By getAuthorizationLoginInput() {
        return By.xpath(AUTHORIZATION_LOGIN_LOCATOR);}

    private static By getAuthorizationPasswordInput() {
        return By.xpath(AUTHORIZATION_PASSWORD_LOCATOR);}

    private static By getLoginButton() {
        return By.xpath(AUTHORIZATION_LOGIN_BUTTON_LOCATOR);}
}
