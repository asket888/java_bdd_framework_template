package pages;

import locators.FrontEndLocator;
import org.openqa.selenium.By;


public class FrontEndPage implements FrontEndLocator {

    public static By getDefaultPageElements() {
        return By.xpath(FRONT_END_ALL_FIELDS_LOCATOR);}
}
