package com.project.todoappspringboot.controller;


import com.project.todoappspringboot.models.TodoItem;
import com.project.todoappspringboot.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneId;

@Controller
public class TodoFormController {

    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;


    //Homepage ✅
    @GetMapping("/")
    public ModelAndView index() {
        logger.debug("Get Request");
        logger.info("Testing to get Request!");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemRepository.findAll()); //pulling all the items from db

        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        modelAndView.addObject("date", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate());

        return modelAndView;
    }

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

    //     PART: EDIT button ✅
    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView("update-to-do");
        TodoItem todoItem = todoItemRepository.findById(id).get();
        modelAndView.addObject("todo", todoItem);
        return modelAndView;
    }

    // PART: Save new data (update) ✅
    // update by id:
    @PostMapping("/todo/{id}")
    public String save(@ModelAttribute TodoItem todoItem) {
        todoItem.setCreatedDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);

        return "redirect:/";
    }

    // Update new data to database ✅
    @PostMapping("/todo")
    public String todoSave(@ModelAttribute TodoItem todoItem) {
        todoItem.setCreatedDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);

        return "redirect:/";
    }

}
