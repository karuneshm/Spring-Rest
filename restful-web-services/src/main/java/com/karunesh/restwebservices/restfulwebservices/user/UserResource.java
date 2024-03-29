package com.karunesh.restwebservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User getuser(@PathVariable(name = "id") Integer id) {
		User user = service.findById(id);
		if(user == null) {
			throw new UserNotFoundException("id -"+id);
			
		}
		
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteuser(@PathVariable(name = "id") Integer id) {
		User user = service.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id -"+id);
		}
		return ResponseEntity.noContent().build();
	}

}
