package com.cucumbertest.featureImpl;

import cucumber.api.Scenario;
import cucumber.api.java8.En;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CucumberImpl implements En {

    private RequestSpecification request;
    private Response response;
    private Scenario scenario;
    private Filter logFilter;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        logFilter = new CustomLogFilter();
        request = RestAssured.with();
    }

    public CucumberImpl() {

        Given("I configure Request-ContentType {string}", (String string) -> {
            request.given()
                    .filter(logFilter)
                    .contentType(ContentType.JSON);
        });

        And("I invoke request type {string} with resource path {string}", (String invoketype, String resourcePath) -> {
            // Write code here that turns the phrase above into concrete actions
            request.baseUri("https://reqres.in/api");
            response = request.get(resourcePath);
            if (logFilter instanceof CustomLogFilter) {
                CustomLogFilter customLogFilter = (CustomLogFilter) logFilter;
                scenario.write("\n" + "API Request: " + customLogFilter.getRequestBuilder()
                        + "\n" + "API Response: " + customLogFilter.getResponseBuilder());
            }
        });

        Then("I receive status code {int}", (Integer statuscode) -> {
            // Write code here that turns the phrase above into concrete actions
            response.then().assertThat().statusCode(statuscode);
        });
    }
}
