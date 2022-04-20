package Tests;

import Base.TestBase;
import Client.RestClient;
import Utils.TestUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetAPITest extends TestBase {
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
    public void GetTestwithoutheaders() throws IOException {
        restClient = new RestClient();
        httpResponse=restClient.Get(url);

        //a. statuscode
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String code = Integer.toString(statusCode);
        System.out.println("Status code is" + statusCode);
        Assert.assertEquals(code,prop.getProperty("StatusCode_200"));

        //b. responseBody
        String body = EntityUtils.toString(httpResponse.getEntity(),"UTF-8"); //get body in string format
        JSONObject responseJson = new JSONObject(body); //get body in json format
        System.out.println(responseJson);

        //c. single value assertion
        String perPageValue = TestUtils.getValueByJsonPath(responseJson,"/per_page");
        System.out.println("per page" +perPageValue);
        Assert.assertEquals("6",perPageValue);

        //d. get All header values
        Header headers[] =httpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap();

        for(Header header : headers){
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers:" + allHeaders);

        //e. get the value from json array
        String lastname = TestUtils.getValueByJsonPath(responseJson,"/data[0]/last_name");
        String id = TestUtils.getValueByJsonPath(responseJson,"/data[0]/id");
        String first_name = TestUtils.getValueByJsonPath(responseJson,"/data[0]/first_name");
        String email = TestUtils.getValueByJsonPath(responseJson,"/data[0]/email");

        System.out.println(lastname);
        System.out.println(id);
        System.out.println(first_name);
        System.out.println(email);


    }

    //Get method with headers
    @Test
    public void GetTestwithheader() throws IOException {
        restClient = new RestClient();
        httpResponse=restClient.Get(url);

        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Content-Type","application/json");

        //a. statuscode
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String code = Integer.toString(statusCode);
        System.out.println("Status code is" + statusCode);
        Assert.assertEquals(code,prop.getProperty("StatusCode_200"));

        //b. responseBody
        String body = EntityUtils.toString(httpResponse.getEntity(),"UTF-8"); //get body in string format
        JSONObject responseJson = new JSONObject(body); //get body in json format
        System.out.println(responseJson);

        //c. single value assertion
        String perPageValue = TestUtils.getValueByJsonPath(responseJson,"/per_page");
        System.out.println("per page" +perPageValue);
        Assert.assertEquals("6",perPageValue);

        //d. get All header values
        Header headers[] =httpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap();

        for(Header header2 : headers){
            allHeaders.put(header2.getName(), header2.getValue());
        }
        System.out.println("Headers:" + allHeaders);

        //e. get the value from json array
        String lastname = TestUtils.getValueByJsonPath(responseJson,"/data[0]/last_name");
        String id = TestUtils.getValueByJsonPath(responseJson,"/data[0]/id");
        String first_name = TestUtils.getValueByJsonPath(responseJson,"/data[0]/first_name");
        String email = TestUtils.getValueByJsonPath(responseJson,"/data[0]/email");

        System.out.println(lastname);
        System.out.println(id);
        System.out.println(first_name);
        System.out.println(email);




    }

}
