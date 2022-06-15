package br.com.compass.mscustomer.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.mscustomer.dto.UserDto;
import br.com.compass.mscustomer.dto.UserFormDto;
import br.com.compass.mscustomer.entity.User;
import br.com.compass.mscustomer.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public UserDto findById(Long id) {
		User user = userRepository.findById(id).orElse(null);
		UserDto userDto = new UserDto();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setSex(user.getSex());
		userDto.setBirthdate(user.getBirthdate());
		userDto.setEmail(user.getEmail());
		userDto.setActive(user.getActive());
		return userDto;
	}

	public UserDto save(@Valid UserFormDto userFormDto) {
		userRepository.save(new User(userFormDto));
		UserDto userDto = new UserDto();
		userDto.setFirstName(userFormDto.getFirstName());
		userDto.setLastName(userFormDto.getLastName());
		userDto.setSex(userFormDto.getSex());
		userDto.setBirthdate(userFormDto.getBirthdate());
		userDto.setEmail(userFormDto.getEmail());
		userDto.setActive(userFormDto.getActive());
		return userDto;
	}

	public UserDto updateById(@Valid UserFormDto userFormDto, Long id) {
		User user = userRepository.findById(id).orElse(null);
		user.setFirstName(userFormDto.getFirstName());
		user.setLastName(userFormDto.getLastName());
		user.setSex(userFormDto.getSex());
		user.setCpf(userFormDto.getCpf());
		user.setBirthdate(userFormDto.getBirthdate());
		user.setEmail(userFormDto.getEmail());
		user.setPassword(userFormDto.getPassword());
		user.setActive(userFormDto.getActive());
		
		UserDto userDto = new UserDto();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setSex(user.getSex());
		userDto.setBirthdate(user.getBirthdate());
		userDto.setEmail(user.getEmail());
		userDto.setActive(user.getActive());		
		return userDto;
	}
}
