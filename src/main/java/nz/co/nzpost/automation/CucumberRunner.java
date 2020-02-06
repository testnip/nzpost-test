package nz.co.nzpost.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  monochrome = true,
  plugin = {
    "pretty",
    "html:target/cucumber-html-report",
    "json:target/cucumber-json-report.json",
    "junit:target/cucumber-junit.xml"},
  tags = {"~@wip","@automation,@geolocation"},
  features = {"src/main/resources/features"}
)
public class CucumberRunner {

}

