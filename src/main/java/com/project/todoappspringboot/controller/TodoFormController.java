package com.project.todoappspringboot.controller;


import com.project.todoappspringboot.models.TodoItem;
import com.project.todoappspringboot.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;

@Controller
public class TodoFormController {

    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;


    //    PART: ADD button ✅
    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem) {
        return "add-todo-item";
    }


    //     PART: DELETE button ✅
    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id:" +id + "is not found"));

        todoItemRepository.delete(todoItem);
        return "redirect:/";
    }


    //     PROBLEM HERE 🛑
    //     PART: EDIT button 🛑
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id: "+id+ "not found"));

        model.addAttribute("todo", todoItem);
        return "update-todo-item";
    }


}
