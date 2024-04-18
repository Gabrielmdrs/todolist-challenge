package br.com.gabriel.todolistchallenge.controller;

import br.com.gabriel.todolistchallenge.entity.ToDo;
import br.com.gabriel.todolistchallenge.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    @Autowired
    private ToDoService service;
    @GetMapping
    public List<ToDo> list(){
        return service.list();
    }
    @PostMapping
    public List<ToDo> create(@RequestBody ToDo toDo){
        return service.create(toDo);
    }
    @PutMapping
    public List<ToDo> update(@RequestBody ToDo toDo){
        return service.update(toDo);
    }
    @DeleteMapping("/{id}")
    public List<ToDo> delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

}
