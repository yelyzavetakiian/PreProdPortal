package http.client;

import consumer.PostConsumer;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;

public class RestClient {
    private HttpClient httpClient = HttpClientBuilder.create().build();
    private String baseUrl;

    private static final Logger LOGGER = Logger.getLogger(PostConsumer.class);

    public RestClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setHeaders(HttpRequest request) {
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
    }

    public HttpResponse get(String url) {
        HttpUriRequest request = new HttpGet(baseUrl + url);
        setHeaders(request);
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            LOGGER.error("Can not execute request. HTTP method: GET, URL: " + baseUrl + url, e);
            throw new IllegalArgumentException();
        }
    }

    public HttpResponse post(String url, String requestBody) {
        HttpPost request = new HttpPost(baseUrl + url);
        try {
            StringEntity params = new StringEntity(requestBody);
            request.setEntity(params);
            setHeaders(request);
            return httpClient.execute(request);
        } catch (IOException e) {
            LOGGER.error("Can not execute request. HTTP method: POST, URL: " + baseUrl + url, e);
            throw new IllegalArgumentException();
        }
    }

    public HttpResponse put(String url, String requestBody) {
        HttpPut request = new HttpPut(baseUrl + url);
        try {
            StringEntity params = new StringEntity(requestBody);
            request.setEntity(params);
            setHeaders(request);
            return httpClient.execute(request);
        } catch (IOException e) {
            LOGGER.error("Can not execute request. HTTP method: PUT, URL: " + baseUrl + url, e);
            throw new IllegalArgumentException(e);
        }
    }

    public HttpResponse delete(String url) {
        HttpDelete request = new HttpDelete(baseUrl + url);
        setHeaders(request);
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            LOGGER.error("Can not execute request. HTTP method: DELETE, URL: " + baseUrl + url, e);
            throw new IllegalArgumentException(e);
        }
    }
}
