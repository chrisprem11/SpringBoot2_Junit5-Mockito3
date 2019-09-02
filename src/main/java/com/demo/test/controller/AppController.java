package com.demo.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.test.model.Greeting;
import com.demo.test.service.GreetingService;

@RestController
public class AppController {

	@Autowired
	private GreetingService greetingService;

	@GetMapping("/")
	public ResponseEntity<List<Greeting>> greetWorld(@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "sort_property", required = false) String sortProperty,
			@RequestParam(name = "sort_ascending", defaultValue = "true", required = false) boolean isSortAscending) {
		return new ResponseEntity<>(greetingService.findAllGreetings(pageNo, pageSize, sortProperty, isSortAscending),
				HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") int id) {
		Greeting greet = greetingService.findById(id).get();
		return new ResponseEntity<>(greet, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Greeting> saveGreeting(@RequestBody Greeting greeting) {
		Greeting greet = greetingService.saveGreeting(greeting);
		return new ResponseEntity<>(greet, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGreeting(@PathVariable("id") int id) {
		greetingService.deleteGreetingById(id);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}

}
