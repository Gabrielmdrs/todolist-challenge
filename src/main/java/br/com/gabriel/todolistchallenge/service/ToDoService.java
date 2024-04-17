package br.com.gabriel.todolistchallenge.service;


import br.com.gabriel.todolistchallenge.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository repository;

}
