package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import steps.BaseTest;

import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class ElementManagementUtil extends BaseTest {

    private DataTypeConverterUtil converterUtil = new DataTypeConverterUtil();

    public Cookie getSessionCookies() {

        Set<Cookie> cookies = driver.manage().getCookies();
        Iterator<Cookie> itr = cookies.iterator();
        Cookie cookie = itr.next();

        return cookie;
    }

    public void elementIsVisible (By elementBy) {

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    public void elementIsNotVisible (By elementBy) {

        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
    }

    public boolean isElementPresent(By by){

        driver.findElement(by);
        return true;
    }

    public void clickButton(By buttonBy) {

        driverWait.until(elementToBeClickable(buttonBy));
        driver.findElement(buttonBy).click();
    }

    public void setSpecificValueIntoInput(By inputLocator, String value) {

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(inputLocator));
        driver.findElement(inputLocator).sendKeys(value);
    }

    public void setSpecificValueIntoInputAfterClear (By inputLocator, String value) {

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(inputLocator));
        driver.findElement(inputLocator).clear();
        driver.findElement(inputLocator).sendKeys(value);
    }

    public List<String> getAllElementsFromPage(By elementBy) {

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));

        List<WebElement> webElementList = driver
                .findElements(elementBy);

        return converterUtil
                .convertWebElementListToStringList(webElementList);
    }

    public List<String> getAllDataFromDropdown(By dropDownBy, By dropdownDataBy) {

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(dropDownBy));
        driver.findElement(dropDownBy).click();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(dropdownDataBy));

        List<WebElement> webElementList = driver
                .findElements(dropdownDataBy);

        return converterUtil
                .convertWebElementListToStringList(webElementList);
    }

    private Select getSelectElement(WebElement selectElement) {
        return new Select(selectElement);
    }

    public void setSpecificValueInDropDown(By dropDownBy, String value) {

        driverWait
                .until(elementToBeClickable(dropDownBy));
        getSelectElement(driver
                .findElement(dropDownBy)).selectByValue(value);
    }

    public void setSpecificTextInDropDown(By dropDownBy, String text) {

        driverWait
                .until(elementToBeClickable(dropDownBy));
        getSelectElement(driver
                .findElement(dropDownBy)).selectByVisibleText(text);
    }

    public void setSpecificIndexInDropdown(By dropDownBy, int index) {

        driverWait
                .until(elementToBeClickable(dropDownBy));
        getSelectElement(driver
                .findElement(dropDownBy)).selectByIndex(index);
    }

    public List<String> getErrorMessage(By elementBy) {

        driverWait
                .until(ExpectedConditions.visibilityOfElementLocated(elementBy));

        elementIsVisible(elementBy);
        List<String> errorList = new ArrayList<>();
        Collections.addAll(errorList, driver
                .findElement(elementBy).getText());

        return converterUtil
                .convertTrimStringList(errorList);
    }
}
