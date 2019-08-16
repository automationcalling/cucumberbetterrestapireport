package com.cucumbertest.executor;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/Features/" }, glue = {
        "com.cucumbertest.featureImpl" }, monochrome = true, plugin = { "pretty", "json:target/report.json","html:target/report"})
public class CucumberRunnerTest {
}
