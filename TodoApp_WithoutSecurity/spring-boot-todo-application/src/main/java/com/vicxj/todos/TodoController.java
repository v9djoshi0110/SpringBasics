package com.vicxj.todos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

	@Autowired
	private TodoHardCodedService todoService;

	@GetMapping(path = "/users/{username}/todos")
	public List<Todos> getAllTodos(@PathVariable String username) {

		return todoService.findAllTodos();
	}

	@GetMapping(path = "/users/{username}/todos/{id}")
	public Todos getTodoById(@PathVariable String username, @PathVariable long id) {

		return todoService.checkAvailableId(id);
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id, @PathVariable String username) {

		Todos todoDeleted = todoService.deleteById(id);
		if (todoDeleted != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todos> updateTodoById(@PathVariable long id, @PathVariable String username,
			@RequestBody Todos todo) {

		Todos todoUpdated = todoService.save(todo);

		return new ResponseEntity<Todos>(todoUpdated, HttpStatus.OK);
	}

	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> addTodo(@PathVariable String username, @RequestBody Todos todo) {

		if (todo.getUsername() == null) {
			todo.setUsername(username);
		}
		Todos todoAdded = todoService.save(todo);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoAdded.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
}
