package com.example.openapiexample;

import com.example.openapiexample.rest.GreetingApi;
import com.example.openapiexample.rest.model.RestUpdateGreetingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Optional.ofNullable;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class GreetingController implements GreetingApi {

    private String interjection = "hello";
    private String noun = "world";

    @Override
    public ResponseEntity<String> getGreeting() {
        return ok(interjection + " " + noun);
    }

    @Override
    public ResponseEntity<String> updateGreeting(RestUpdateGreetingRequest restUpdateGreetingRequest) {
        ofNullable(restUpdateGreetingRequest.getInterjection())
            .ifPresent(interjection -> this.interjection = interjection);
        ofNullable(restUpdateGreetingRequest.getNoun())
            .ifPresent(noun -> this.noun = noun);
        return getGreeting();
    }
}
