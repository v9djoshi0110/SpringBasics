package com.vicxj.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {

	@GetMapping(path = "/hello-world")
	public String getHelloWorld() {
		throw new RuntimeException("Some error has occured. Please contact support at....911-911-9119");
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {

		return new HelloWorldBean("Hello World!123");
	}

	@GetMapping(path = "/hello-world/{name}")
	public HelloWorldBean getHelloWorld(@PathVariable String name) {

		return new HelloWorldBean(String.format("Hello World!.. %s", name));
	
	}
	
	
}
