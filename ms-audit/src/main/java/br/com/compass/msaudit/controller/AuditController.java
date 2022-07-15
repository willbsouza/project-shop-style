package br.com.compass.msaudit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/audit")
public class AuditController {

	@GetMapping
	public String testController() {
		return "Testing controller MS Audit";
	}
}
