package com.bilouro.utils;

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient{

    private final String url;

    public WebClient(String url) {
        this.url = url;
    }

    public HttpResponse post(String json) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(json));
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            return httpClient.execute(post);
        } catch (Exception e) {
            return null;
        }
    }

}