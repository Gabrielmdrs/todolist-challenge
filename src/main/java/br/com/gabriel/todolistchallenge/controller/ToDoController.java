package br.com.gabriel.todolistchallenge.controller;

import br.com.gabriel.todolistchallenge.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ToDoController {
    @Autowired
    private ToDoService service;

}
