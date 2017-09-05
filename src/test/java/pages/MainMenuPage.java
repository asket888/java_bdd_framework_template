package pages;

import locators.MainMenuLocator;
import org.openqa.selenium.By;
import utils.ElementManagementUtil;

public class MainMenuPage implements MainMenuLocator {

    private ElementManagementUtil elementManagementUtil = new ElementManagementUtil();

    public void goToPage(String mainMenuTab, String pageLink) {

        elementManagementUtil
                .clickTheElement(getMenuTab(mainMenuTab));
        elementManagementUtil
                .clickTheElement(getPageLink(mainMenuTab, pageLink));
    }

    public void goToCertificatePage(String mainMenuTab) {

        elementManagementUtil
                .clickTheElement(getMenuTab(mainMenuTab));
    }

    public static By getMenuTab(String mainMenuTab) {

        By getMenuTab = null;

        if (mainMenuTab.equals("Action1")) {
            getMenuTab = clickAction1DropDown();
        } else if (mainMenuTab.equals("Action2")) {
            getMenuTab = clickAction2DropDown();
        } else { System.out.println("There is no such Tab like " + mainMenuTab + " on Main Menu." +
                " Please check your Gherkin input data");
        }
        return getMenuTab;
    }

    public static By getPageLink (String mainMenuTab, String pageLink) {

        By getPageLink = null;

        switch (mainMenuTab) {
            case("Action1"):
                if (pageLink.equals("Page1")) {
                    getPageLink = goToAction1Page1();
                } else if (pageLink.equals("Page2")) {
                    getPageLink = goToAction1Page2();
                } else if (pageLink.equals("Page3")) {
                    getPageLink = goToAction1Page3();
                }
                else { System.out.println("There is no such Link like " + pageLink + " in Actions Tab." +
                            " Please check your Gherkin input data");
                }
                break;

            case("Action2"):
                if (pageLink.equals("Page1")) {
                    getPageLink = goToAction2Page1();
                } else if (pageLink.equals("Page2")) {
                    getPageLink = goToAction2Page2();
                } else if (pageLink.equals("Page3")) {
                    getPageLink = goToAction2Page3();
                }
                else { System.out.println("There is no such Link like " + pageLink + " in Pending Tab." +
                            " Please check your Gherkin input data");
                }
                break;
        }
        return getPageLink;
    }

    public boolean checkIfPagePresent(String pageName) {

        return elementManagementUtil
                .checkElementIsPresent(checkPageTitle(pageName));
    }

    // Page title
    public By checkPageTitle(String pageName) {
        return By.xpath("//h1[contains(text(),'" + pageName + "')]");}

    // Main frontend tabs
    private static By clickAction1DropDown() {
        return By.xpath(LINK_TO_ACTION1_LOCATOR);}

    private static By clickAction2DropDown() {
        return By.xpath(LINK_TO_ACTION2_LOCATOR);}

    // Action1 drop-down input
    private static By goToAction1Page1() {
        return By.xpath(LINK_TO_ACTION1_PAGE1_LOCATOR);}

    private static By goToAction1Page2() {
        return By.xpath(LINK_TO_ACTION1_PAGE2_LOCATOR);}

    private static By goToAction1Page3() {
        return By.xpath(LINK_TO_ACTION1_PAGE3_LOCATOR);}

    // Action2 drop-down input
    private static By goToAction2Page1() {
        return By.xpath(LINK_TO_ACTION2_PAGE1_LOCATOR);}

    private static By goToAction2Page2() {
        return By.xpath(LINK_TO_ACTION2_PAGE2_LOCATOR);}

    private static By goToAction2Page3() {
        return By.xpath(LINK_TO_ACTION2_PAGE3_LOCATOR);}
}
