package steps;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import pages.ServicesPage;
import utils.ElementManagementUtil;

import java.util.List;

public class BackEndSteps {

    private Cookie cookie = new ElementManagementUtil().getSessionCookies();
    private ServicesPage servicesPage = new ServicesPage();
    private static String responseBody;

    @Given("^Check Internal service endpoint \"([^\"]*)\" response \"([^\"]*)\"$")
    public void userSendRequestToInternalServicesByInstrumentType(String endpoint, String expectedElementType) {

        String actualElementType = servicesPage
                .getInternalServiceGetPortfolioElementType(endpoint.concat(expectedElementType), cookie);

        Assert.assertEquals(actualElementType, expectedElementType);
    }

    @Given("^Send POST request with following json body to endpoint \"([^\"]*)\"$")
    public void sendPOSTRequestWithFollowingJsonBodyToEndpoint(String endpoint, List<String> jsonBodyList) {

        String jsonBodyString = "";

        for (String element: jsonBodyList) {
            jsonBodyString = jsonBodyString.concat(element);
        }

        responseBody = servicesPage
                .getInternalServicePostResponseBody(endpoint, jsonBodyString, cookie);
    }

    @Then("^Following response Json file received from the Internal service$")
    public void followingResponseJsonFileReceivedFromTheInternalService(List<String> expectedJsonList) {

        String expectedJsonString = "";

        for (String element: expectedJsonList) {
            expectedJsonString = expectedJsonString.concat(element);
        }

        String actualJsonString = responseBody;

        Assert.assertEquals(expectedJsonString.trim(), actualJsonString.trim());

    }
}
