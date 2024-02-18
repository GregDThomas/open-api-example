package com.example.openapiexample;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Slf4j
@CucumberContextConfiguration
@ActiveProfiles("cucumber-test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CucumberSpringContextLoader {

}