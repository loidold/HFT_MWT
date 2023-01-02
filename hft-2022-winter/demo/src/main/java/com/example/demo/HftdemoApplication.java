package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
@SpringBootApplication
// makes class eligible for incoming REST calls
@RestController
@RequestMapping("/api/old")
public class HftdemoApplication {

	private String privateProperty = "unset";

	//@RequestMapping("/")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String sayHello(){

		return "Good morning lovely people of HfT Stuttgart";
	}

	@GetMapping("/helloTo/{name}")
	public String sayHelloTo(@PathVariable String name){

		privateProperty = name;
		return "Hello: "+name;
	}

	// @PostMapping("/somePath") equivalent to
	// @RequestMapping(value = "/somePath", method = RequestMethod.POST)

	@GetMapping("/helloAgain")
	public String sayHelloAgain(){

		return "Hello fromXX another Rest endpoint";
	}

	public static void main(String[] args) {
		SpringApplication.run(HftdemoApplication.class, args);
	}

	// Richardson Maturity Model

	/*
	 * Level 0: Swamp of Pox
	 * 
	 * not using verbs, not using nouns, using HTTP verbs in wrong way
	 * 
	 */

	 @GetMapping("/setProperty")
	 public String setSomething(@RequestParam String something){

		this.privateProperty = something;

		return "";

	 }

	/*
	 * Level 1: Using nouns
	 * 
	 * using nouns/resources
	 * 
	 */

	@GetMapping("/property")
	public String getSomething(){

	   return this.privateProperty;

	}

	/*
	 * Level 2: Using nouns and verbs (and status codes)
	 * 
	 * not verbs and nouns correctly and meaningful
	 * 
	 */

	@Operation(summary = "Set the current value of a property")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = "Created the new value", content = {@Content})
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/property/{newProperty}")
	public String setSomethingNew(@PathVariable String newProperty){

		this.privateProperty = newProperty;

		return newProperty;

	}

	// Level 3: Hypermedia?

}
