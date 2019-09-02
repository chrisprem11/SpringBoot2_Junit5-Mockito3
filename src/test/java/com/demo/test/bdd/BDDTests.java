package com.demo.test.bdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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
public class BDDTests {

	@Mock
	private GreetingRepository greetingRepository;

	@InjectMocks
	private GreetingService greetingService;

	private Greeting greeting;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		greeting = new Greeting(1, "Hello BDDMockito");
	}

	@Test
	public void save() {
		given(greetingRepository.save(any(Greeting.class))).willReturn(greeting);
		Greeting savedGreeting = greetingService.saveGreeting(new Greeting());
		then(greetingRepository).should().save(any(Greeting.class));
		assertThat(savedGreeting).isNotNull();
	}

	@Test
	public void testFindByID() {
		given(greetingRepository.findById(1)).willReturn(Optional.of(greeting));
		Greeting greetingFound = greetingService.findById(1).get();
		then(greetingRepository).should().findById(1);
		assertThat(greetingFound).isNotNull();
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
		then(greetingRepository).should().deleteById(1);
	}
}
