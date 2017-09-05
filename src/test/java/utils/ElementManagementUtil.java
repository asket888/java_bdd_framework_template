package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import steps.setup.BaseTest;

import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class ElementManagementUtil extends BaseTest {

    private DataTypeConverterUtil converterUtil = new DataTypeConverterUtil();
    private final String LIBS_UPLOADS_FOLDER = System.getProperty("user.dir")+"\\libs\\uploads\\";

    // Page Elements Action methods
    public void elementIsVisible (By elementBy) {

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    public void elementIsNotVisible (By elementBy) {

        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));
    }

    public void clickTheElement(By buttonBy) {

        driverWait.until(elementToBeClickable(buttonBy));
        driver.findElement(buttonBy).click();
    }

    public void moveCursorToElement(By elementBy) {

        elementIsVisible(elementBy);
        driverActions.moveToElement(driver.findElement(elementBy)).perform();
    }

    public void acceptBrowserAlert () {

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean checkElementIsPresent(By by){

        driver.findElement(by);
        return true;
    }


    // Page Elements Getters methods
    public Cookie getSessionCookies() {

        Set<Cookie> cookies = driver.manage().getCookies();
        Iterator<Cookie> itr = cookies.iterator();
        Cookie cookie = itr.next();

        return cookie;
    }

    public List<String> getAllElementsTextAsStringList(By elementBy) {

        elementIsVisible(elementBy);

        List<WebElement> webElementList = driver
                .findElements(elementBy);

        return converterUtil
                .convertWebElementListToStringListByText(webElementList);
    }

    public List<String> getAllElementsValueAsStringList(By elementBy) {

        elementIsVisible(elementBy);

        List<WebElement> webElementList = driver
                .findElements(elementBy);

        return converterUtil
                .convertWebElementListToStringListByAttribute(webElementList, "value");
    }

    public String getOneElementTextAsString(By elementBy) {

        elementIsVisible(elementBy);

        WebElement webElement = driver
                .findElement(elementBy);
        String string = webElement.getText();

        return string.trim();
    }

    public String getOneElementBackgroundAsString(By elementBy, String setAttribute) {

        elementIsVisible(elementBy);

        WebElement webElement = driver
                .findElement(elementBy);

        return webElement
                .getCssValue(setAttribute);
    }

    public List<String> getAllValuesFromDropDownAsStringList(By dropDownBy, By dropdownDataBy) {

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(dropDownBy));
        driver.findElement(dropDownBy).click();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(dropdownDataBy));

        List<WebElement> webElementList = driver
                .findElements(dropdownDataBy);
        List<String> stringList = converterUtil
                .convertWebElementListToStringListByText(webElementList);

        return stringList;
    }


    // Page Elements Setters methods
    public void setValueIntoInputWithoutClear(By inputLocator, String value) {

        elementIsVisible(inputLocator);
        driver.findElement(inputLocator).sendKeys(value);
    }

    public void setValueIntoInputAfterClear(By inputLocator, String value) {

        elementIsVisible(inputLocator);
        driver.findElement(inputLocator).clear();
        driver.findElement(inputLocator).sendKeys(value);
    }


    public void setValueInDropDown(By dropDownBy, String value) {

        driverWait
                .until(elementToBeClickable(dropDownBy));

        new Select(driver
                .findElement(dropDownBy)).selectByValue(value);
    }

    public void setTextInDropDown(By dropDownBy, String text) {

        driverWait
                .until(elementToBeClickable(dropDownBy));
        new Select(driver
                .findElement(dropDownBy)).selectByVisibleText(text);
    }

    public void setIndexInDropdown(By dropDownBy, int index) {

        driverWait
                .until(elementToBeClickable(dropDownBy));
        new Select(driver
                .findElement(dropDownBy)).selectByIndex(index);
    }

    public void setFileUploadPathToElement(By elementBy, String fileName) {

        WebElement fileInput = driver.findElement(elementBy);
        fileInput.sendKeys(LIBS_UPLOADS_FOLDER  + fileName);
    }
}
