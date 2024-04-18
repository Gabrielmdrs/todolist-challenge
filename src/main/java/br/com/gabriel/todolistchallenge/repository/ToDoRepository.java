package br.com.gabriel.todolistchallenge.repository;

import br.com.gabriel.todolistchallenge.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Long> {
}
