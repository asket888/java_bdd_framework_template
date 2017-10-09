package steps.backend;


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
    private static String responseCode;
    private static String responseBody;

    @Given("^Sent GET http request on External service \"([^\"]*)\"$")
    public void userSendRequestToExternalServices(String serviceUrl) {

        responseCode = servicesPage
                .getExternalServiceGetResponseCode(serviceUrl);
    }

    @Then("^Receive response code \"([^\"]*)\" from External service$")
    public void receiveResponseCodeFromExternalService(String expectedCode) {

        System.out.println(responseCode);

        Assert.assertEquals(expectedCode, responseCode);
    }

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
