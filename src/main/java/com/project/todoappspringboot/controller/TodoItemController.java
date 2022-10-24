package com.project.todoappspringboot.controller;

import com.project.todoappspringboot.models.TodoItem;
import com.project.todoappspringboot.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;
import java.time.Instant;
import java.time.ZoneId;

@Controller
public class TodoItemController {

    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

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

        return modelAndView;
    }


    // When user click update button -> data will be update: 🆘 🆘
    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-todo-item";
        }

        todoItem.setCreatedDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            todoItem.setId(id);
            return "update-todo-item";
        }

        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

}
