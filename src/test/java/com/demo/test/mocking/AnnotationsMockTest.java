package com.demo.test.mocking;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnnotationsMockTest {

	@Mock
	Map<String, Object> mapMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMock() {
		mapMock.put("Key", "FooValue");
	}
}
