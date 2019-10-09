package com.skateboard.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SkateBoardResourceTest {
	
	@InjectMocks
	SkateBoardResource skateBoardResource;

	@Test
	public void sayHelloReturnsHello() {
		String actualValue = skateBoardResource.sayhello();
		assertEquals("hello",actualValue);
	}

}
