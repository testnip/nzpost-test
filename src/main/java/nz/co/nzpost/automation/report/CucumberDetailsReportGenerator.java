package nz.co.nzpost.automation.report;

import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CucumberDetailsReportGenerator implements ReportGenerator {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * How to use single page report generator http://mkolisnyk.blogspot.co.nz/2015/06/cucumber-jvm-advanced-reporting-2.html
   */
  public void generate() {
    CucumberDetailedResults results = new CucumberDetailedResults();
    results.setOutputDirectory("target/cucumber-single-page-report/");
    results.setOutputName("cucumber-results");
    results.setSourceFile("target/cucumber-json-report.json");
    results.setScreenShotLocation("screenshots/");
    try {
      results.execute(true);
    } catch (Exception e) {
      log.warn("Unable to generate cucumber report in a single page!");
    }
  }
}
