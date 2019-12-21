package com.amouchere;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Test {


   // @org.junit.jupiter.api.Test
    public void test() throws IOException, InterruptedException {

        for (int i = 0; i < 5; i++) {
            HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
//        HttpPost post = new HttpPost("https://temp-mon-js.herokuapp.com:443/api/temps");


            Thread.sleep(1000);
            HttpPost post2 = new HttpPost("http://localhost:8080/api/data?location=salon");
            StringEntity params2 = new StringEntity("{\"temperature\": \"" + i + 5 + "\" , \"humidity\": \"60\"}");
            post2.setHeader("Content-type", "application/json");
            post2.setEntity(params2);
            httpClient.execute(post2);
            Thread.sleep(1000);
        }


    }
}
