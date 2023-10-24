package com.eder.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data//importa tudo do lombok
@Entity
@Table(name = "usuario")
public class UserModel {
	@Id	
	@GeneratedValue(generator = "UUID")
	private UUID id;
	@Column(unique = true)//nao permite repetir o usuario
	private String username;
	private String name;
	private String password;
	@CreationTimestamp
	private LocalDateTime dataDeCriacao;
		
	
	

}
