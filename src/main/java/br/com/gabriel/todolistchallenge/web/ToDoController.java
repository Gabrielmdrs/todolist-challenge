package br.com.gabriel.todolistchallenge.web;

import br.com.gabriel.todolistchallenge.entity.ToDo;
import br.com.gabriel.todolistchallenge.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class ToDoController {
    @Autowired
    private ToDoService service;


    @Operation(description = "List all the ToDos")
    @ApiResponse(responseCode = "200",description = "Return a list of ToDos")
    @GetMapping
    public List<ToDo> list(){
        return service.list();
    }

    @Operation(description = "Create a new ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create a new ToDo")})
    @PostMapping
    @Transactional
    public ResponseEntity<List<ToDo>> create(@RequestBody @Valid ToDo toDo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(toDo));
    }

    @Operation(description = "Update a exist ToDo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update performed"),
            @ApiResponse(responseCode = "400", description = "Not exist ToDo with id informed")})
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<List<ToDo>> upDate(@PathVariable Long id, @RequestBody ToDo toDo){
        return ResponseEntity.status(HttpStatusCode.valueOf(200))
                .body(service.update(id, toDo));
    }

    @Operation(description = "Delete ToDO by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete performed"),
            @ApiResponse(responseCode = "400", description = "Not exist ToDo with id informed")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public List<ToDo> delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

}
