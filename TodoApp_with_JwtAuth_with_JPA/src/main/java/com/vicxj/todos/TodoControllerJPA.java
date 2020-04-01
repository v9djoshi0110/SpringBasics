package com.vicxj.todos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class TodoControllerJPA {

	@Autowired
	private TodoHardCodedService todoService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;

	@GetMapping(path = "/jpa/users/{username}/todos")
	public List<Todos> getAllTodos(@PathVariable String username) {

		// find all todos by username
		return todoJpaRepository.findByUsername(username);
		//return todoService.findAllTodos();
	}

	@GetMapping(path = "/jpa/users/{username}/todos/{id}")
	public Todos getTodoById(@PathVariable String username, @PathVariable long id) {

		return todoJpaRepository.findById(id).get();
		//return todoService.checkAvailableId(id);
	}

	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id, @PathVariable String username) {

		if(todoJpaRepository.findById(id).get()!=null) {
			todoJpaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todos> updateTodoById(@PathVariable long id, @PathVariable String username,
			@RequestBody Todos todo) {
		Todos todos = todoJpaRepository.findById(id).get();
		todos.setDescription(todo.getDescription());
		todos.setUsername(todo.getUsername());
		todos.setTargetDate(todo.getTargetDate());
		todos.setDone(todo.getDone());
		
		Todos todoUpdated = todoJpaRepository.save(todos);

		return new ResponseEntity<Todos>(todoUpdated, HttpStatus.OK);
	}

	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> addTodo(@PathVariable String username, @RequestBody Todos todo) {

		if (todo.getUsername() == null) {
			todo.setUsername(username);
		}
		Todos todoAdded = todoJpaRepository.save(todo);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoAdded.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
}
