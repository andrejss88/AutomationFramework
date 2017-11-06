package com.github.Factories;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ClientFactory {

    public static CloseableHttpClient getDefaultClient(){
        return HttpClientBuilder.create().build();
    }
    public static CloseableHttpClient getClientWithCredentials(String userName, String passwd) {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, passwd);
        provider.setCredentials(AuthScope.ANY, credentials);
        return HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();
    }
}
