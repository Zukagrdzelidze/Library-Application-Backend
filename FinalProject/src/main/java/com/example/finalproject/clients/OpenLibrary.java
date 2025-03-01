package com.example.finalproject.clients;

import com.example.finalproject.constants.OpenLibraryConstants;
import com.example.finalproject.constants.ProxyConstant;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.List;


@NoArgsConstructor
@Component
public class OpenLibrary extends ExternalApi {

    /**
        Gets JSON Object from External Api
     */
    public JSONObject getJsonFromLibrary(String title){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(setUpHTTPRequestFactory());

        restTemplate.setInterceptors(
                List.of(new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException, IOException {
                        System.out.println("Request: " + request.getURI());
                        System.out.println("Request headers: " + request.getHeaders());
                        return execution.execute(request, body);
                    }
                })
        );

        //"http://openlibrary.org/search.json?q=lord_of_the_rings"
        ResponseEntity<String> response = restTemplate.exchange(UriComponentsBuilder.newInstance().scheme(OpenLibraryConstants.SCHEME)
                        .host(OpenLibraryConstants.HOST)
                        .path(OpenLibraryConstants.PATH).query(OpenLibraryConstants.QUERY)
                        .buildAndExpand(title).toUriString(),
                HttpMethod.GET, setUpHttpEntity(), String.class);
        JSONObject jsonResponse  = new JSONObject(response.getBody());
        return jsonResponse;
    }



}
