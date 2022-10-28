package br.com.training.controller;

import javax.validation.Valid;

import br.com.training.dto.UserDtoRequest;
import br.com.training.dto.UserDtoResponse;
import br.com.training.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.training.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserDtoResponse> createUser(@RequestBody @Valid final UserDtoRequest dto) {
        userService.saveUser(dto);
		return new ResponseEntity<>(UserDtoResponse.castByDTO(dto),HttpStatus.CREATED);
	}

	@GetMapping("/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserDtoResponse> findByCpf (@PathVariable final String cpf){ //valor da variavel é mapeado no URL /variavel
		User user = userService.findByCpf(cpf);
		UserDtoResponse dto = new UserDtoResponse(user.getName(), user.getEmail(), String.valueOf(user.getBirthDate()));
		return ResponseEntity.ok().body(dto);
	}

	@PutMapping("/{cpf}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<UserDtoResponse> updateUser(@PathVariable String cpf,@Valid @RequestBody UserDtoRequest dto){
		userService.update(cpf, dto.castUserDtobyUser());
		return ResponseEntity.ok(UserDtoResponse.castByDTO(dto));
	}

	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteUser(@PathVariable String cpf){
		userService.delete(cpf);
		return ResponseEntity.ok("Usuario apagado");
	}

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<UserDtoResponse>> findAll(){
		List<User> users = userService.findAll();
		List<UserDtoResponse> dtosResponse = new ArrayList<>();
		for (User user : users) {
			String name = user.getName();
			String email = user.getEmail();
			String birthDate = String.valueOf(user.getBirthDate());
			UserDtoResponse response = new UserDtoResponse(name,email,birthDate);
			dtosResponse.add(response);
		}
		return ResponseEntity.ok(dtosResponse);
	}

}
