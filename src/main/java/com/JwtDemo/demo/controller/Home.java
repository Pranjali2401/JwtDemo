package com.JwtDemo.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	@RequestMapping("/home")
	public String Page() {
		return "This is Private page";
	}
}