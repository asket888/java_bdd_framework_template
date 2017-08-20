package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.Cookie;
import steps.BaseTest;

import java.io.IOException;

import static utils.PropertyGetterUtil.getPropertyValue;

public class ApiHttpRequTestUtil extends BaseTest {

    private static HttpPost setPostRequest(
            String endpointName, String certificateName, String jsonName, Cookie cookie) throws IOException {

        InputOutputUtil inputOutput = new InputOutputUtil();

        HttpPost postRequest = new HttpPost(
                getPropertyValue("db.server.url") + endpointName + "/" + certificateName);
        postRequest.setHeader("Cookie", "GTSESSION=" + cookie.getValue());
        postRequest.setHeader("content-type", "application/json");
        postRequest.setHeader("cache-control", "no-cache");

        StringEntity entity = new StringEntity(inputOutput.jsonReader(
                endpointName + "/" + certificateName + "_" + jsonName));

        postRequest.setEntity(entity);

        return postRequest;
    }

    public static String getServiceResponseCode(String serviceUrl) {

        String responseCode = null;

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet getRequest = new HttpGet(serviceUrl);
            System.out.println("Requesting from : " + getRequest.getURI());

            HttpResponse getResponse = httpClient.execute(getRequest);
            responseCode = String
                    .valueOf(getResponse
                            .getStatusLine()
                            .getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    public static String getServiceResponceJsonBody(
            String endpointName, String certificateName, String jsonName, Cookie cookie) {

        String prettyJsonOutput = null;
        String uglyJsonOutput = null;

        try {

            InputOutputUtil inputOutput = new InputOutputUtil();
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpResponse postResponse = httpClient
                    .execute(setPostRequest(endpointName, certificateName, jsonName, cookie));

            uglyJsonOutput = inputOutput
                    .bufferReader(postResponse
                            .getEntity()
                            .getContent());

            prettyJsonOutput = new DataTypeConverterUtil()
                    .convertPrettyJsonOutput(uglyJsonOutput);

//            System.out.println("Output from Server: " + prettyJsonOutput);

            httpClient.getConnectionManager().shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return prettyJsonOutput;
    }
}
