package com.vicxj.todos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardCodedService {

	private static List<Todos> todos = new ArrayList<>();
	private static Long idCounter = 0L;

	static {
		todos.add(new Todos(++idCounter, "vicxj", "Learn Swimming", new Date(), false));
		todos.add(new Todos(++idCounter, "vicxj", "Learn playing Guitar", new Date(), false));
		todos.add(new Todos(++idCounter, "vicxj", "Learn Angular JS", new Date(), false));
		todos.add(new Todos(++idCounter, "vicxj", "Learn Microservices", new Date(), false));

	}

	public List<Todos> findAllTodos() {

		return todos;
	}

	public Todos save(Todos todo) {

		if (todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			
		Todos currentTodo=	checkAvailableId(todo.getId());
		currentTodo.setId(todo.getId());
		currentTodo.setDescription(todo.getDescription());
		currentTodo.setUsername(todo.getUsername());
		currentTodo.setDone(todo.getDone());
		currentTodo.setTargetDate(todo.getTargetDate());
		//deleteById(todo.getId());
		//todos.add(todo);
		}
		return todo;
	}

	public Todos deleteById(long id) {
		Todos todo = checkAvailableId(id);

		if (todo == null) {
			return null;
		}

		if (todos.remove(todo)) {
			return todo;
		}
		return null;
	}

	public Todos checkAvailableId(long id) {

		for (Todos todo : todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
}
