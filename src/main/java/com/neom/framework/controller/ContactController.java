package com.neom.framework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sindalah.entity.Contact;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ContactController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${url}")
    private String url;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContacts(@RequestHeader(name = "Authorization") String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        log.info("url-------->"+url);
        headers.set("Authorization",   accessToken); //accessToken can be the secret key you generate.
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<Contact>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Contact>>() {
                });

        List<Contact> contactInfo = responseEntity.getBody();
        log.info("Contact Info" + contactInfo.toString());
        log.info("response status:: " + responseEntity.getStatusCode());
        return responseEntity;
    }
}
