package br.com.gabriel.todolistchallenge.web;

import br.com.gabriel.todolistchallenge.entity.ToDo;
import br.com.gabriel.todolistchallenge.service.ToDoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    @Transactional
    public ResponseEntity<List<ToDo>> create(@RequestBody @Valid ToDo toDo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(toDo));
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<List<ToDo>> update(@PathVariable Long id, @RequestBody ToDo toDo){
        return ResponseEntity.status(HttpStatusCode.valueOf(200))
                .body(service.update(id, toDo));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public List<ToDo> delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

}
