package br.com.gabriel.todolistchallenge;

import br.com.gabriel.todolistchallenge.entity.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TestConstants {
    public static final List<ToDo> TODOS = new ArrayList<>() {
        {
            add(new ToDo(9995L, "@gabrielmedeiros", "test", false, 1));
            add(new ToDo(9996L, "@gabrielmedeiros", "test2", false, 1));
            add(new ToDo(9997L, "@gabrielmedeiros", "test3", false, 1));
            add(new ToDo(9998L, "@gabrielmedeiros", "test4", false, 1));
            add(new ToDo(9999L, "@gabrielmedeiros", "test5", false, 1));
        }
    };

    public static final ToDo TODO = TODOS.get(0);
}



