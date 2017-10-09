package steps.frontend;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import pages.MainMenuPage;
import steps.setup.BaseDriver;

public class MainMenuSteps extends BaseDriver {

    private MainMenuPage mainMenuPage = new MainMenuPage();

    @Given("^User goes to \"(.*?)\" -> \"(.*?)\" page$")
    public void userGoesToPage(String mainMenuTab, String pageLink) {

        mainMenuPage
                .goToPage(mainMenuTab, pageLink);
    }

    @Then("^User goes to Certificate List page$")
    public void userGoesToPage() {

        mainMenuPage
                .goToCertificatePage("Certificates");
    }

    @Then("^\"(.*?)\" page is displayed$")
    public void checkIfPageIsDisplayed(String pageName) {

        mainMenuPage
                .checkCurrentPageName(pageName);

        Assert.assertTrue(
                "There is no such Element like "+ pageName +" on the page. Please check your Gherkin input data",
                mainMenuPage
                        .checkIfPagePresent(pageName));
    }
}
