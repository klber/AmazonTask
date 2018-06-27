package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "classpath:features/amazon.feature"
	,glue= {"seleniumglue"}
)

public class AmazonTest {

}

