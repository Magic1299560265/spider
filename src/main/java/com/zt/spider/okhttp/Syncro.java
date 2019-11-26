package com.zt.spider.okhttp;

import org.apache.http.HttpHost;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class Syncro {
    public Syncro() {

        CloseableHttpClient httpclient = null;
        CloseableHttpClient httpclient222 = null;

        try {

            HttpGet httpget = new HttpGet("https://raw.github.com/square/okhttp/master/README.md");
            // Request configuration can be overridden at the request level.
            // They will take precedence over the one set at the client level.

            RequestConfig defaultRequestConfig = RequestConfig.custom()
//                .setCookieSpec(CookieSpecs.DEFAULT)
//                .setExpectContinueEnabled(true)
//                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
//                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                    .build();

            RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
//                .setProxy(new HttpHost("myotherproxy", 8080))
                    .build();
            httpget.setConfig(requestConfig);
            // Create an HttpClient with the given custom dependencies and configuration.
            httpclient = HttpClients.custom()
//                .setConnectionManager(connManager)
//                .setDefaultCookieStore(cookieStore)
//                .setDefaultCredentialsProvider(credentialsProvider)
//                .setProxy(new HttpHost("myproxy", 8080))
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .build();
            HttpClientContext context = HttpClientContext.create();

            CloseableHttpResponse response = null;


            try {
                response = httpclient.execute(httpget, context);

                System.out.println(EntityUtils.toString(response.getEntity()));

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



//        CloseableHttpResponse response = httpclient.execute(httpget,null);

    }

    public static void main(String[] args) {
        new Syncro();
    }
}
