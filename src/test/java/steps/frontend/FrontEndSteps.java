package steps.frontend;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.FrontEndPage;

import java.util.List;
import java.util.Map;

import static pages.FrontEndPage.*;

public class FrontEndSteps {

    private FrontEndPage frontEndPage = new FrontEndPage();

    @And("^Checks all following fields display on Page3 page$")
    public void checkAllFieldsDisplayOnThePage(List<String> expectedFields) {

        List<String> actualFields = frontEndPage
                .getElementsFromThePage(getFieldsFromThePage());

        Assert.assertEquals("Unexpected element or missed element", expectedFields, actualFields);
    }

    @And("^Checks all following buttons display on Page3 page$")
    public void checkAllButtonsDisplayOnThePage(List<String> expectedButtons) {

        List<String> actualFields = frontEndPage
                .getElementsFromThePage(getButtonsFromThePage());

        Assert.assertEquals("Unexpected element or missed element", expectedButtons, actualFields);
    }

    @And("^User fills all fields on Page3 page with following data$")
    public void userSearchesForTransactionsWithTheFollowingData(Map<String, String> inputValues) {

        frontEndPage.fillAllMandatoryFields(inputValues);
    }

    @When("^User clicks 'Submit' button on Page3 page$")
    public void userClicksSubmitButton() {

        frontEndPage.clickSubmitButton();
    }

    @Then("^(\\d+) events are displayed in Result block$")
    public void eventsAreDisplayedInResultBlock(int expectedEventsNum) {

        int actualEventsNum = frontEndPage.checkAvailableEvents().size();

        Assert.assertEquals(actualEventsNum, expectedEventsNum);
    }
}
