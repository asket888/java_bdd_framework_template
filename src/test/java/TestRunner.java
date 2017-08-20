import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/test-results", "rerun:target/cucumber/test-results/rerun.txt"},
        features = {"src/test/resources"},
//        features = { "@target/cucumber/test-results/rerun.txt" }, //rerun just failed tests
        tags = {}, //include ("@rebalance") or exclude ("~@rebalance") tags
        dryRun = false
)

public class TestRunner {
}
