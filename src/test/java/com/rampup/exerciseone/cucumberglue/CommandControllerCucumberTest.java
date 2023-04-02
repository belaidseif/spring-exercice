package com.rampup.exerciseone.cucumberglue;


import com.rampup.exerciseone.dto.CommandDto;
import com.rampup.exerciseone.dto.DetailDto;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CommandControllerCucumberTest {

    @LocalServerPort
    String port;
    ResponseEntity<?> response;


    @When("the client calls endpoint {string}")
    public void whenClientCalls(String url) {

            response = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, null,
                    Object.class);

    }

    @Then("response status code is {int}")
    public void thenStatusCode(int expected) {
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(expected, 200);
    }

    @Then("response list size is {int}")
    public void thenListSize(int size) {

        Assertions.assertEquals(size,((List<CommandDto>) response.getBody()).size());
    }


    @Then("response command must be like this:$")
    public void thenLCommandId(Map<String,String> map) {
        Map<String, Object> mapResponse =(Map<String, Object>) response.getBody();

        Assertions.assertEquals(Integer.parseInt(map.get("details_size")) ,((List) mapResponse.get("details")).size());
        Assertions.assertEquals(Integer.parseInt(map.get("id")) ,mapResponse.get("id"));
        Assertions.assertEquals(map.get("createdAt") ,mapResponse.get("createdAt"));


    }

}
