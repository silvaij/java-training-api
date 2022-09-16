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

import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody @Valid final User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> findByCpf (@PathVariable final String cpf){ //valor da variavel é mapeado no URL /variavel
		User user = userService.findByCpf(cpf);
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/{cpf}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<User> updateUser(@PathVariable String cpf,@Valid @RequestBody User user){
		User userUpdate = userService.update(cpf, user);
		return ResponseEntity.ok(userUpdate);
	}

	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable String cpf){
		userService.delete(cpf);
	}

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<User>> findAll(){
		return ResponseEntity.ok(userService.findAll());
	}

}
