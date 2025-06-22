package com.valdo;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    glue = {"com.valdo"},
    features = {"src/test/resources"},
    plugin = {"pretty","html:target/cucumber-reports.html"},
    tags = "not @exclude"
)

public class CucumberTest {

}
