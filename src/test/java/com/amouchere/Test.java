package com.amouchere;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Test {

    @org.junit.jupiter.api.Test
    public void test() throws IOException {

        for (int i = 0; i < 10; i++) {
            HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
//        HttpPost post = new HttpPost("https://temp-mon-js.herokuapp.com:443/api/temps");
            HttpPost post = new HttpPost("http://localhost:8080/api/temps");
            StringEntity params = new StringEntity("{\"value\": \"" + i + "\" , \"location\": \"Chambre 1\"}");
            post.setHeader("Content-type", "application/json");
            post.setEntity(params);
            HttpResponse response = httpClient.execute(post);
        }


    }
}
