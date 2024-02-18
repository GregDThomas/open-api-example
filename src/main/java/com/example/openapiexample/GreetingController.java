package com.example.openapiexample;

import com.example.openapiexample.rest.GreetingApi;
import com.example.openapiexample.rest.model.RestUpdateGreetingRequest;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Optional.ofNullable;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class GreetingController implements GreetingApi {

    private static final String DEFAULT_INTERJECTION = "hello";
    private static final String DEFAULT_NOUN = "world";
    private String interjection = DEFAULT_INTERJECTION;
    private String noun = DEFAULT_NOUN;

    @VisibleForTesting
    public void resetGreeting() {
        this.interjection = DEFAULT_INTERJECTION;
        this.noun = DEFAULT_NOUN;
    }

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
