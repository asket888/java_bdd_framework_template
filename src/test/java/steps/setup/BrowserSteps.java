package steps.setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.*;
import pages.AuthorizationPage;

import static utils.PropertyGetterUtil.getPropertyValue;

public class BrowserSteps extends BaseDriver {

    @Given("^Login into Application as SuperUser$")
    public static void loginIntoAmcAsTrader() {

        getDriver();

        driver.navigate()
                .to(getPropertyValue("app.server.url"));

        new AuthorizationPage().loginIntoApp(
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
