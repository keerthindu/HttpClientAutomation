package Client;

import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    //1. GET Method
    public CloseableHttpResponse Get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet); //hit the url
        return httpResponse;
    }

    //2. Get method with headers
    public CloseableHttpResponse Get(String url, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet); //hit the url

        for(Map.Entry<String, String> entry: headerMap.entrySet()){
            httpGet.addHeader(entry.getKey(),entry.getValue());
        }

        return httpResponse;

    }

    //3. POST method
    public CloseableHttpResponse POSTRequest(String url, String entityString, HashMap<String, String> headerMap ) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(entityString));

        for(Map.Entry<String, String> entry: headerMap.entrySet()){
            httpPost.addHeader(entry.getKey(),entry.getValue());
        }
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        return httpResponse;
    }

    //4. PUT method
    public CloseableHttpResponse PUT(String url, String entityString, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpPut htttpPut = new HttpPut(url);
        htttpPut.setEntity(new StringEntity(entityString));

        for(Map.Entry<String,String> entry : headerMap.entrySet()){
            htttpPut.addHeader(entry.getKey(),entry.getValue());
        }
        //Execute the PUT request
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(htttpPut);
        return closeableHttpResponse;
    }

    //5. DELETE method
    public CloseableHttpResponse DELETE(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);
        return httpResponse;

    }
}

