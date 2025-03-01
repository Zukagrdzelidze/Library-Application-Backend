package com.example.finalproject.clients;

import com.example.finalproject.constants.OpenLibraryConstants;
import com.example.finalproject.constants.ProxyConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;

@Component
public class ExternalApi {

    /**
        Setting Up HTTPENTITY
     */
    protected HttpEntity<String> setUpHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(OpenLibraryConstants.ACCEPT, OpenLibraryConstants.APP_JSON);
        headers.set(OpenLibraryConstants.CONNECTION, OpenLibraryConstants.KEEP_ALIVE);
        headers.set(OpenLibraryConstants.ACCEPT, OpenLibraryConstants.TYPE);
        headers.set(OpenLibraryConstants.KEEP_ALIVE, OpenLibraryConstants.KEEP_ALIVE_SECOND);
        headers.set(OpenLibraryConstants.CACHE_CONTROL, OpenLibraryConstants.NO_CACHE);
        headers.set(OpenLibraryConstants.USER_AGENT, OpenLibraryConstants.POSTMAN_VERSION);
        headers.set(OpenLibraryConstants.HOST_KEYWORD, OpenLibraryConstants.HOST);
        headers.set(OpenLibraryConstants.ENCODE, OpenLibraryConstants.ENCODE_METHODS);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        return entity;
    }

    protected ClientHttpRequestFactory setUpHTTPRequestFactory(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(Duration.ofSeconds(90)); // 90 seconds
        requestFactory.setReadTimeout(Duration.ofSeconds(90)); // 90 seconds
        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyConstant.HOSTNAME, 8080)));
        return requestFactory;
    }
}
