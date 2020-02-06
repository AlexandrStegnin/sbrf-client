package com.stegnin.sbrfclient.service;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;

/**
 * @author Alexandr Stegnin
 */

@Service
@VaadinSessionScope
public class RestService {

    private final RestTemplate restTemplate;
    private final InetAddress proxy;
    private final int port;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.port = 8989;
        this.proxy = InetAddress.getLoopbackAddress();
        Authentication auth = getAuth();
        this.restTemplate = restTemplateBuilder
                .basicAuthentication(auth.getName(), auth.getCredentials().toString())
                .build();
    }

    public String getUserInfo() {
        String url = String.format("http://%s:%d/secured", proxy.getHostName(), port);
        return restTemplate.getForObject(url, String.class);
    }

    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
