package integrationTest;


import com.sainsburys.productscrapper.Application;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class ApplicationIntegrationTest {

    @Autowired
    private Application application;

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void should_contain_result_in_the_console_output_response() {

        // Act
        application.main("sampleOutPut");
        String consoleOutput = outputCapture.toString();

        // Assert

        assertThat(consoleOutput.contains("results")).isTrue();
    }


}