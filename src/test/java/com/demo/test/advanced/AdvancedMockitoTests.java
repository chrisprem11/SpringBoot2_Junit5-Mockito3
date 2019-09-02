package com.demo.test.advanced;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.test.model.Greeting;
import com.demo.test.repository.GreetingRepository;
import com.demo.test.service.GreetingService;

@SpringBootTest
public class AdvancedMockitoTests {

	@Mock
	private GreetingRepository greetingRepository;

	@InjectMocks
	private GreetingService greetingService;

	private Greeting greeting;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		greeting = new Greeting(1, "Hello Advanced Mockito");
	}

	@Test
	public void testFindByIdException() {
		given(greetingRepository.findById(anyInt())).willThrow(NullPointerException.class);
		assertThrows(NullPointerException.class, () -> greetingService.findById(anyInt()));
		then(greetingRepository).should().findById(anyInt());
	}

	@Test
	public void testDeleteByIdException() {
		willThrow(NullPointerException.class).given(greetingRepository).deleteById(anyInt());
		assertThrows(NullPointerException.class, () -> greetingService.deleteGreetingById(anyInt()));
		then(greetingRepository).should().deleteById(anyInt());
	}
	
}
