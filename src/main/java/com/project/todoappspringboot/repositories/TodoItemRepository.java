package com.project.todoappspringboot.repositories;

import com.project.todoappspringboot.models.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
}
