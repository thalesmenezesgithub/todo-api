package org.github.cursodsousa.todo.rest;

import java.time.LocalDateTime;
import java.util.*;
import org.github.cursodsousa.todo.model.Todo;
import org.github.cursodsousa.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/todos")
public class TodoController
{
    @Autowired
    private TodoRepository repository;

    //Método de salvar
    @PostMapping
    public Todo save(@RequestBody Todo todo)
    {
        return repository.save(todo);
    }

    //Método de deletar
    // http://localhost:8080/api/todos/1
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id)
    {
        repository.deleteById(id);
    }

    //Método listar todas as tarefas
    @GetMapping
    public List<Todo> getAll()
    {
        return repository.findAll();
    }

    // url/api/todos/1
    @GetMapping("{id}")
    public Todo getById(@PathVariable Long id)
    {
        return repository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // http:localhost:8080/api/todos/1/done
    @PatchMapping("{id}/done") //Patch atualiza apenas uma parte
    public Todo markAsDone(@PathVariable Long id)
    {
        return repository.findById(id).map
        (todo ->
        {
            todo.setDone(true);
            todo.setDoneDate(LocalDateTime.now());
            repository.save(todo);

            return todo;
        }).orElse(null);
    }
}