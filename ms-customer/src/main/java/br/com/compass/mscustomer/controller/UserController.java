package br.com.compass.mscustomer.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mscustomer.dto.UserDto;
import br.com.compass.mscustomer.dto.UserFormDto;
import br.com.compass.mscustomer.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable Long id) {
		return new ResponseEntity<UserDto>(userService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> save(@RequestBody @Valid UserFormDto userFormDto) {
		return new ResponseEntity<UserDto>(userService.save(userFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> update(@RequestBody @Valid UserFormDto userFormDto, @PathVariable Long id) {
		return new ResponseEntity<UserDto>(userService.updateById(userFormDto, id), HttpStatus.OK);
	}
}