package com.eder.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
/**
 * METODO VERIFICA SE EXISTE UM USUARIO COM ESTE USERNAME
 * @param username
 * @return
 */
	UserModel findByUsername(String username);
}
	