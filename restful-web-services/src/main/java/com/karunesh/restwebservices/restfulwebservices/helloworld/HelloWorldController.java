package com.karunesh.restwebservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/hello-world")
	public String getHelloWorld() {
		return "Hello-World";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello-World");
	}
	
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean getHelloWorld(@PathVariable(name="name") String name) {
		return new HelloWorldBean("Hello" +name);
	}

}
