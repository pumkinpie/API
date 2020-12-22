package com.tts.UsersAPI.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tts.UsersAPI.Repository.UsersRepository;
import com.tts.UsersAPI.model.User;

@RestController
public class UserController {
	@Autowired
	private UsersRepository  usersRepository;
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(@RequestParam(value="state", required=false) String state){
	if (state != null) {
		return new ResponseEntity<>( usersRepository.findByState(state), HttpStatus.OK);
	}
		return new ResponseEntity<>( usersRepository.findAll(), HttpStatus.OK);
	}
	@GetMapping("/users/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable(value="id")Long id){
		Optional<User> user = usersRepository.findById(id);
		if(!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else{
			 usersRepository.save(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
	@PutMapping("/users/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable(value="id") Long id, @Valid @RequestBody  User user, BindingResult bindingResult){
		if(! usersRepository.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			 usersRepository.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value="id") Long id){
		if(! usersRepository.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		 usersRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}