package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import pages.MainMenuPage;
import steps.setup.BaseTest;
import utils.ElementManagementUtil;

public class MainMenuSteps extends BaseTest {

    private ElementManagementUtil elementManagementUtil = new ElementManagementUtil();
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

        elementManagementUtil
                .elementIsVisible(mainMenuPage.checkPageTitle(pageName));

        Assert.assertTrue(
                "There is no such Element like "+ pageName +" on the page. Please check your Gherkin input data",
                mainMenuPage
                        .checkIfPagePresent(pageName));
    }
}
