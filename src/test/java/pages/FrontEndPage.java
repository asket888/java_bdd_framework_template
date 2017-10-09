package pages;

import locators.FrontEndLocator;
import org.openqa.selenium.By;
import utils.ElementManagementUtil;

import java.util.List;
import java.util.Map;


public class FrontEndPage implements FrontEndLocator {

    private ElementManagementUtil elementManagement = new ElementManagementUtil();

    public List<String> getElementsFromThePage(By elementsLocator) {

        return elementManagement
                .getAllElementsTextAsStringList(elementsLocator);
    }

    public void fillAllMandatoryFields(Map<String, String> values) {

        elementManagement
                .setValueIntoInputAfterClear(getFromInput(), values.get("Calculation Date"));
        elementManagement
                .setValueIntoInputAfterClear(getToInput(), values.get("Calculation Date"));
        elementManagement
                .setTextInDropDown(getSelectElementDropDown(), values.get("Select Certificate"));
    }

    public void clickSubmitButton() {

        elementManagement
                .clickTheElement(getSubmitButton());
    }

    public List<String> checkAvailableEvents() {

        return elementManagement
                .getAllElementsTextAsStringList(getAllEvents());
    }

    public static By getFieldsFromThePage() {
        return By.xpath(FRONT_END_ALL_FIELDS_LOCATOR);}

    public static By getButtonsFromThePage() {
        return By.xpath(FRONT_END_ALL_BUTTONS_LOCATOR);}

    private static By getFromInput() {
        return By.xpath(FRONT_END_FROM_INPUT_LOCATOR);}

    private static By getToInput() {
        return By.xpath(FRONT_END_TO_INPUT_LOCATOR);}

    public static By getSelectElementDropDown() {
        return By.xpath(FRONT_END_SELECT_ELEMENT_LOCATOR);}

    public static By getSubmitButton() {
        return By.xpath(FRONT_END_SUBMIT_BUTTON_LOCATOR);}

    public static By getAllEvents() {
        return By.xpath(FRONT_END_ALL_EVENTS_LOCATOR);}

}
