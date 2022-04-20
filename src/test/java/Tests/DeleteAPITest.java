package Tests;

import Base.TestBase;
import Client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class DeleteAPITest extends TestBase {

    TestBase testbase;
    String ServiceURL;
    String apiURL;
    String deleteuserURL;
    String URL;
    RestClient restClient;
    HttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setup(){
        testbase = new TestBase();
        ServiceURL = prop.getProperty("URL");
        apiURL = prop.getProperty("ServiceURL");
        deleteuserURL = prop.getProperty("deleteuserURL");
        URL = ServiceURL+apiURL+deleteuserURL;
    }

    @Test
    public void DeleteAPItest() throws IOException {
        restClient = new RestClient();
        closeableHttpResponse = restClient.DELETE(URL);

        int code = closeableHttpResponse.getStatusLine().getStatusCode();
        String statusCode = Integer.toString(code);
        System.out.println("Status code is--->" + statusCode);

        Assert.assertEquals(statusCode,prop.getProperty("StatusCode_204"));


    }
}
