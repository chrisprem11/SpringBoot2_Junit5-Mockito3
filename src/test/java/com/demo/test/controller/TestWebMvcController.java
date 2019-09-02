package com.demo.test.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.demo.test.model.Greeting;
import com.demo.test.service.GreetingService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AppController.class)
public class TestWebMvcController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GreetingService greetingService;

	private Greeting greet;

	private List<Greeting> greetings = new ArrayList<>();

	@Before
	public void setUp() {
		greet = Greeting.builder().id(1).message("MyMessage").build();
		greetings.add(greet);
		given(greetingService.findAllGreetings(1, 1, "id", true)).willReturn(greetings);
	}

	@After
	public void tearDown() {
		reset(greetingService);
	}

	@Test
	public void testFindAllController() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testFindByIDInTestController() throws Exception {
		given(greetingService.findById(1)).willReturn(Optional.of(greet));
		MvcResult result = mockMvc.perform(get("/{id}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.message").value("MyMessage")).andExpect(jsonPath("$.id").value(greet.getId()))
				.andReturn();
		System.out.println("Result -> " + result.getResponse().getContentAsString());
	}

}
