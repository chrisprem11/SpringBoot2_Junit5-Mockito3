package com.demo.test.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.test.model.Greeting;
import com.demo.test.service.GreetingService;

@RunWith(SpringRunner.class)
public class ApiControllerTest {

	@Mock
	private GreetingService greetingService;

	@InjectMocks
	private AppController controller;

	private List<Greeting> greetings = new ArrayList<>();

	private Greeting greeting;

	MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		greeting = Greeting.builder().id(1).message("MyMessage").build();
		greetings.add(greeting);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		given(greetingService.findAllGreetings(1, 1, "id", true)).willReturn(greetings);
	}

	@DisplayName("Test Get All Greetings")
	@Test
	public void testFindAllController() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testFindByIDController() throws Exception {
		given(greetingService.findById(1)).willReturn(Optional.of(greeting));
		MvcResult result = mockMvc.perform(get("/{id}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.message").value("MyMessage")).andExpect(jsonPath("$.id").value(greeting.getId()))
				.andReturn();
		System.out.println("Result -> " + result.getResponse().getContentAsString());
	}

}
