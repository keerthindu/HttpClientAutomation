package Tests;

import Base.TestBase;
import Client.RestClient;
import Data.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PutAPITest extends TestBase {

    //Create global methods
    TestBase testbase;
    String ServiceURL;
    String apiURL;
    String URL;
    RestClient restClient;
    HttpResponse closeableHttpResponse;

    // in before method call the properties file
    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException, JSONException {

    //Call the constructor of the base class and execute the properties file
        testbase = new TestBase();
        ServiceURL = prop.getProperty("URL");
        apiURL = prop.getProperty("ServiceURL");
        URL = ServiceURL+apiURL;
    }

    //Main method which calls PUT method
    @Test
    public void PUTAPITest() throws ClientProtocolException, IOException, JSONException{

        restClient = new RestClient();

        //Pass the Request Header
        HashMap<String,String> headrMap = new HashMap<String,String>();
        headrMap.put("Content-Type", "application/json");

        ObjectMapper mapper = new ObjectMapper();

//Create object of Users class, new users
        TestData testData = new TestData("Indu","HRManager");

/*Convert java object "user" to JASON Object using writeValue()
and pass the path where to store the JSON file and the object to be converted */

        mapper.writeValue(new File("src/test/java/Data/testData.json"),testData );

//convert java object - &gt; JSON - &gt;String
        String userJsonString = mapper.writeValueAsString(testData);
        System.out.println(userJsonString);
        System.out.println(URL);

//Call the PUT Method
        closeableHttpResponse = restClient.PUT(URL, userJsonString, headrMap);

/*fetch status, header, JSON response from CloseableHttpResponse
Fetch Status Code */

        int code = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code --->" +code);

//Validate the status code using Assert class
        String statuscode = Integer.toString(code);
        Assert.assertEquals(statuscode,prop.getProperty("StatusCode_200"));

/*2.Fetch the JSON String, use EntityUtils class and call to String method where we have to
pass entity and format entity is available in closeableHttpResponse and pass UTF-8 format
because we want a pure string. So complete JSON will be stored in a String, so we need to
convert an entire string into a JSON object */

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

/* From JSON library, call JSON class and pass the response string.
This JSON object converts the string to JSON */
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSONfrom API --->"+responseJson);}


}