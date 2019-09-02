package com.demo.test.utility;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.test.model.Greeting;

@SpringBootTest
public class GreetingTest {

	private Greeting greeting = null;
	
	@BeforeClass
	public static  void beforeClass() {
		System.out.println("In Before Class...");
	}
	
	@Before
	public void setUp() {
		System.out.println("In Before..");
		greeting = new Greeting(0, "Hello Junit");
	}
	
	@Test
	public void helloWorld() {
		greeting.setMessage("First Test Method");
		System.out.println(greeting.getMessage());
	}
	
	@Test
	public void helloWorldAgain() {
		greeting.setMessage("Second Test Method");
		System.out.println(greeting.getMessage());
	}
	
	@After
	public void tearDown() {
		System.out.println("In After...");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("In After Class...");
	}
}
