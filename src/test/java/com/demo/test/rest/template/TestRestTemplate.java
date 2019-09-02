package com.demo.test.rest.template;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplate {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testAllGreetings() {
	}

}
