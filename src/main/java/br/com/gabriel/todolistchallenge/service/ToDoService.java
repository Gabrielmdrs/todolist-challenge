package br.com.gabriel.todolistchallenge.service;


import br.com.gabriel.todolistchallenge.entity.ToDo;
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

    public List<ToDo> update(ToDo toDo){
        repository.save(toDo);
        return list();
    }

    public List<ToDo> delete(Long id){
        repository.deleteById(id);
        return list();
    }


}
