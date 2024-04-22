package br.com.gabriel.todolistchallenge.service;


import br.com.gabriel.todolistchallenge.entity.ToDo;
import br.com.gabriel.todolistchallenge.exception.BadRequestException;
import br.com.gabriel.todolistchallenge.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository repository;

    public List<ToDo> create(ToDo toDo){
        repository.save(toDo);
        return list();
    }
    public List<ToDo> list(){
        Sort sort = Sort.by("priority").descending().and(
                Sort.by("name").ascending()
        );
        return repository.findAll(sort);
    }

    public List<ToDo> update(Long id, ToDo toDo){
        repository.findById(id).ifPresentOrElse((existingTodo) -> {
            toDo.setId(id);
            repository.save(toDo);
        }, () -> {
            throw new BadRequestException("ToDO %d not exist! ".formatted(id));
        });

        return list();

    }

    public List<ToDo> delete(Long id){
        repository.findById(id).ifPresentOrElse((existingTodo) -> repository.delete(existingTodo), () -> {
            throw new BadRequestException("Todo %d not exist! ".formatted(id));
        });
        return list();

    }


}
