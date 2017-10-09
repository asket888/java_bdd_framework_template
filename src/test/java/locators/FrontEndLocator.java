package locators;


public interface FrontEndLocator {

    String FRONT_END_ALL_FIELDS_LOCATOR = "//div[contains(@class,'field')]//label";
    String FRONT_END_ALL_BUTTONS_LOCATOR = "//div[contains(@class,'button')]//label";
    String FRONT_END_FROM_INPUT_LOCATOR = "//input[@id='fromDate']";
    String FRONT_END_TO_INPUT_LOCATOR = "//input[@id='toDate']";
    String FRONT_END_SELECT_ELEMENT_LOCATOR = "//select[@id='selectElement']";
    String FRONT_END_SUBMIT_BUTTON_LOCATOR = "//button[@id='submitButton']";
    String FRONT_END_ALL_EVENTS_LOCATOR = "//div[contains(@class,'events')]//label";
}
