package Tests;

import Base.TestBase;
import Client.RestClient;
import Data.TestData;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostAPITest extends TestBase {
    TestBase testBase;
    String Baseurl;
    String serviceURL;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResponse;

    @BeforeMethod
    public void setUp() throws IOException {
        testBase = new TestBase();
        Baseurl = prop.getProperty("URL");
        serviceURL = prop.getProperty("ServiceURL");
        url = Baseurl+serviceURL;
    }

    @Test
    public void postTest() throws IOException {
        restClient = new RestClient();
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Content-Type","application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        TestData testData = new TestData("morpheus","leader");

        //object to json
        objectMapper.writeValue(new File("src/test/java/Data/testData.json"),testData);

        //Object to json in string]
        String testdatajsonString = objectMapper.writeValueAsString(testData);
        System.out.println(testdatajsonString);

        httpResponse = restClient.POSTRequest(url,testdatajsonString,header);

       //1. statuscode
        int code = httpResponse.getStatusLine().getStatusCode();
       String statuscode = Integer.toString(code);
       Assert.assertEquals(statuscode,prop.getProperty("StatusCode_201"));

       //2. Json String
        String responsestring = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        JSONObject responsejson = new JSONObject(responsestring);
        System.out.println(responsejson);

        //json to java object
        TestData testresobj = objectMapper.readValue(responsestring,TestData.class);
        System.out.println(testresobj);

        Assert.assertTrue(testData.getName().equals(testresobj.getName()));

        Assert.assertTrue(testData.getJob().equals(testresobj.getJob()));

        System.out.println(testresobj.getName() + "   " + testresobj.getJob()+ "   " + testresobj.getId() +"   "+testresobj.getCreatedAt());



    }
}
