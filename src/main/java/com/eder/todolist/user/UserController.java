package com.eder.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/*Modificador
 * public
 * private
 * protected
 * 
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository repository;

	@PostMapping("/")
	public ResponseEntity createUser(@RequestBody UserModel user) {
		//System.out.println(user.getName());
		var users = this.repository.findByUsername(user.getUsername());
		if(users!=null) {
			System.out.println("Usuario já existe!");
			//mensagem de erro e estado Code
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já existe!");
		}
		var senhaCriptografada = BCrypt.withDefaults()
				.hashToString(12,user.getPassword().toCharArray());
		user.setPassword(senhaCriptografada);
		var userCreated = this.repository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
}
