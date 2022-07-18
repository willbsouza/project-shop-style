package br.com.compass.msbffshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bffshop")
public class BffShopController {

	@GetMapping
	public String testController() {
		return "Testing BFF Shop Controller.";
	}
}
