package steps;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.*;
import pages.AuthorizationPage;

import static utils.PropertyGetterUtil.getPropertyValue;

public class BrowserSteps extends BaseTest {

    @Given("^Start browser$")
    public static void startBrowser() {

        getDriver();
    }

    @Given("^Open application main page$")
    public static void openAppMainPage() {
        try {
            driver.navigate().to(getPropertyValue("app.server.url"));
        } catch (UnhandledAlertException e) {
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (NoAlertPresentException ee) {
                throw new PendingException("Some ugly alert breaks our test");
            }
        }
    }

    @Then("^Login into application$")
    public void loginIntoApp () {

        new AuthorizationPage().loginIntoAmc(
                getPropertyValue("app.user.login"),
                getPropertyValue("app.user.password"));
    }

    @Then("^Close browser$")
    public static void closeBrowser() {
        driver.close();
        driver.quit();
        driver = null;
        driverWait = null;
    }

    @After
    public void shoutDown(Scenario scenario) {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        if ("pending".equals(scenario.getStatus())) {
            scenario.write("\n" + "Skipped scenario. No test data in environment to execute scenario: " +
                    scenario.getName());
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        if ("passed".equals(scenario.getStatus())) {
            scenario.write("\n" + scenario.getName() + " ended in '" + scenario.getStatus() + "' status! :)");
        }

        if (null != driver) {
            driver.close();
            driver.quit();
            driver = null;
            driverWait = null;
        }
    }
}
