package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.Cookie;
import steps.setup.BaseTest;

import java.io.IOException;

import static utils.PropertyGetterUtil.getPropertyValue;

public class ApiHttpRequTestUtil extends BaseTest {

    // GET request-response for internal services with Cookie settings
    private static String aspireServerUrl = getPropertyValue("aspire.server");

    private HttpGet setInternalServiceGetRequest(
            String endpointPath, Cookie cookie) throws IOException {

        HttpGet getRequest = new HttpGet(aspireServerUrl.concat(endpointPath));

        getRequest.setHeader("Cookie", "GTSESSION=".concat(cookie.getValue()));
        getRequest.setHeader("content-type", "application/json");
        getRequest.setHeader("cache-control", "no-cache");

        return getRequest;
    }

    public HttpResponse getInternalServiceGetResponse(String endpointPath, Cookie cookie) {

        HttpResponse getResponse = null;

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet getRequest = setInternalServiceGetRequest(endpointPath, cookie);

            getResponse = httpClient
                    .execute(getRequest);

            System.out.println("Requesting from: " + getRequest.getURI());
            System.out.println("Response code: " + getResponse.getStatusLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return getResponse;
    }

    // POST request-response for internal services with Cookie settings
    private HttpPost setInternalServicePostRequest(
            String endpointPath, String jsonPath, Cookie cookie) throws IOException {

        HttpPost postRequest = new HttpPost(aspireServerUrl.concat(endpointPath));

        postRequest.setHeader("Cookie", "GTSESSION=".concat(cookie.getValue()));
        postRequest.setHeader("content-type", "application/json");
        postRequest.setHeader("cache-control", "no-cache");

        StringEntity entity = new StringEntity(jsonPath);

        postRequest.setEntity(entity);

        return postRequest;
    }

    public HttpResponse getInternalServicePostResponse(
            String endpointPath, String jsonPath, Cookie cookie) {

        HttpResponse postResponse = null;

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost postRequest = setInternalServicePostRequest(endpointPath, jsonPath, cookie);

            postResponse = httpClient
                    .execute(postRequest);

            System.out.println("Requesting from: " + postRequest.getURI());
            System.out.println("Response code: " + postResponse.getStatusLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return postResponse;
    }
}
