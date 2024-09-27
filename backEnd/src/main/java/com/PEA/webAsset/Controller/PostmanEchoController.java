package com.PEA.webAsset.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class PostmanEchoController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/api/postmanecho")
    public ResponseEntity<String> postmanEcho(@RequestBody Map<String, Object> requestBody) {
        String postmanEchoUrl = "https://postman-echo.com/post";

        try {
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create HttpEntity with headers and body
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            // Forward the request to Postman Echo service
            ResponseEntity<String> response = restTemplate.postForEntity(postmanEchoUrl, entity, String.class);

            // Return the response from Postman Echo
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred while forwarding the request." + ex);
        }
    }
}

