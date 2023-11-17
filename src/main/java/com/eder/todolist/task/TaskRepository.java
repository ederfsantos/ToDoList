package com.eder.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository  extends JpaRepository<TaskModel,UUID>{
 /**
  * Metodo recebe um id de usuario
  * e retorna a tarefa correspondente ao mesmo.
  * @param idUser
  * @return
  */
	TaskModel findByIdUser(UUID idUser);
}
