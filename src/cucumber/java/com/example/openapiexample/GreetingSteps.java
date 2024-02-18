package com.example.openapiexample;

import com.example.openapiexample.rest.ApiClient;
import com.example.openapiexample.rest.GreetingClient;
import com.example.openapiexample.rest.model.RestUpdateGreetingRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class GreetingSteps {

    @LocalServerPort
    private final int localServerPort;
    private final GreetingController greetingController;
    private GreetingClient greetingClient;
    private String response;

    @Given("the greeting API has been reset")
    public void theGreetingAPIHasBeenReset() {
         greetingController.resetGreeting();
    }

    @Given("I have a greeting API client")
    public void iHaveAGreetingAPIClient() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        final ApiClient apiClient = new ApiClient(restTemplate).setBasePath("http://localhost:%d".formatted(localServerPort));
        greetingClient = new GreetingClient(apiClient);
    }

    @When("I fetch the current greeting")
    public void iFetchTheCurrentGreeting() {
        response = greetingClient.getGreeting();
    }

    @Then("I get a response of {string}")
    public void iGetAResponseOf(String expected) {
        assertThat(response)
            .isEqualTo(expected);
    }

    @When("I set the interjection to {string}")
    public void iSetTheInterjectionTo(String interjection) {
        response = greetingClient.updateGreeting( new RestUpdateGreetingRequest().interjection(interjection));
    }

    @And("I set the noun to {string}")
    public void iSetTheNounTo(String noun) {
        response = greetingClient.updateGreeting( new RestUpdateGreetingRequest().noun(noun));
    }
}
