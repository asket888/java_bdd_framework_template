package pages;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import utils.ApiHttpRequTestUtil;
import utils.InputOutputUtil;

import java.io.IOException;

public class ServicesPage {

    private ApiHttpRequTestUtil apiHttpRequTestUtil = new ApiHttpRequTestUtil();

    public String getInternalServiceGetResponseBody(String endpointPath, Cookie cookie) {

        InputOutputUtil inputOutput = new InputOutputUtil();
        String responseBody = null;

        try {

            responseBody = inputOutput
                    .bufferReader(apiHttpRequTestUtil
                            .getInternalServiceGetResponse(endpointPath, cookie)
                            .getEntity()
                            .getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseBody.trim();
    }

    public String getInternalServiceGetPortfolioElementType(String endpoint, Cookie cookie) {

        String firstElementFromBody = null;

        try {
            String responseBody = getInternalServiceGetResponseBody(endpoint, cookie);

            responseBody = responseBody.replaceAll("\\[", "");
            responseBody = responseBody.replaceAll("\\]", "");

            JSONObject jsonObj = new JSONObject(responseBody);

            firstElementFromBody = jsonObj.getString("portfolioElementType");

            System.out.println(firstElementFromBody);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return firstElementFromBody;
    }

    public String getInternalServicePostResponseBody(
            String endpointPath, String jsonPath, Cookie cookie) {

        InputOutputUtil inputOutput = new InputOutputUtil();
        String uglyJsonOutput = null;

        try {
            uglyJsonOutput = inputOutput
                    .bufferReader(apiHttpRequTestUtil.getInternalServicePostResponse(endpointPath, jsonPath, cookie)
                            .getEntity()
                            .getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return uglyJsonOutput;
    }
}
