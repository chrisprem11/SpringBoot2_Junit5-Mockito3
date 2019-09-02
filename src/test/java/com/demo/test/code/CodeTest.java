package com.demo.test.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.demo.test.model.Greeting;
import com.demo.test.repository.GreetingRepository;
import com.demo.test.service.GreetingService;

@SpringBootTest
public class CodeTest {

	@Mock
	private GreetingRepository greetingRepository;

	@InjectMocks
	private GreetingService greetingService;

	private Greeting greeting;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		greeting = new Greeting(1, "Hello Mockito");
	}

	@Test
	public void save() {
		when(greetingRepository.save(any(Greeting.class))).thenReturn(greeting);
		Greeting savedGreeting = greetingService.saveGreeting(new Greeting());
		verify(greetingRepository).save(any(Greeting.class));
		assertThat(savedGreeting).isNotNull();
	}

	@Test
	public void testFindByID() {
		when(greetingRepository.findById(1)).thenReturn(Optional.of(greeting));
		assertEquals(greeting, greetingService.findById(1).get());
		verify(greetingRepository).findById(1);
	}

	@Test
	public void findAll() {
		List<Greeting> greetings = new ArrayList<>();
		greetings.add(greeting);
		Pageable pageable = PageRequest.of(1, 1, Sort.by("id").ascending());
		Page<Greeting> greetingsPagedList = new PageImpl<>(greetings, pageable, 1);
		// given
		given(greetingRepository.findAll(pageable)).willReturn(greetingsPagedList);
		// when

		List<Greeting> greetingsFound = greetingService.findAllGreetings(1, 1, "id", true);
		// then
		then(greetingRepository).should().findAll(pageable);
		assertThat(greetingsFound).hasSize(1);
	}

	@Test
	public void testDeleteById() {
		greetingService.deleteGreetingById(1);
		verify(greetingRepository).deleteById(1);
	}

}
