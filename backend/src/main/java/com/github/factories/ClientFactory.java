package com.github.factories;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class ClientFactory {

    public static CloseableHttpClient getDefaultClient() {

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(getDefaultConfig())
                .setConnectionManager(getDefaultConnectionManager())
                .build();
    }

    public static CloseableHttpClient getClientWithCredentials(String userName, String passwd) {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, passwd);
        provider.setCredentials(AuthScope.ANY, credentials);

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(getDefaultConfig())
                .setDefaultCredentialsProvider(provider)
                .build();
    }

    private static RequestConfig getDefaultConfig(){
        int timeoutMillis = 5000;
        return RequestConfig.custom()
                // time to wait to setup a connection to another process
                .setConnectTimeout(timeoutMillis)

                //  connection manager to give you one connection (because all busy) to make the request
                .setConnectionRequestTimeout(timeoutMillis)

                //the time waiting for data after the connection was established;
                // i.e. maximum time of inactivity between two data packets
                .setSocketTimeout(timeoutMillis)
                .build();
    }

    /**
     * Overrides the default of 2 concurrent connections
     * More info @ https://stackoverflow.com/questions/7683574/apache-httpclient-does-not-make-more-than-2-connections
     */
    private static HttpClientConnectionManager getDefaultConnectionManager() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setDefaultMaxPerRoute(10);
        return manager;
    }
}
