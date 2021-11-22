package com.testing;

import static com.testing.HelloWorldController.MESSAGE_KEY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Map;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Value;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional

public class HelloWorldIntegrationTest {
    
    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate template;

    private Map<String, String> result;
    private String url;
	@Value("${spring.application.name}")
    private String testappname;
	@Value("${server.servlet.context-path}")
    private String testcontextname;
    
    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void responseShouldContainHelloWorldKey() {
        url = "http://localhost:" + port + testcontextname;
		
        
        ResponseEntity<Map> response = template.getForEntity(url, Map.class);
        result = response.getBody();

        assertThat(result.containsKey(MESSAGE_KEY)).isTrue();
        //assertThat(result.get(MESSAGE_KEY)).isEqualTo(testappname);
		assertThat(testcontextname.replace("/","")).isEqualTo(result.get(MESSAGE_KEY));
    }
}
