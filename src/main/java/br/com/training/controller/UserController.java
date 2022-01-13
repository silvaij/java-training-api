package br.com.training.controller;

import javax.validation.Valid;

import br.com.training.dto.UserDto;
import br.com.training.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.training.model.User;
import br.com.training.repository.UserRepository;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	private UserRepository userRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody @Valid User user) {
		return userRepository.save(user);
	}

	@GetMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> findByCpf (@PathVariable String cpf){
		UserDto dto = userService.findByCpf(cpf);
		return ResponseEntity.ok().body(dto);
        //return userRepository.findByCpf(cpf).body(user);
    }


}
