package com.project.todoappspringboot.config;

import com.project.todoappspringboot.models.TodoItem;
import com.project.todoappspringboot.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class TodoItemDataLoader implements CommandLineRunner {

    private  final Logger logger = LoggerFactory.getLogger(TodoItemDataLoader.class);

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if(todoItemRepository.count() == 0) {
            TodoItem todoItem1 = new TodoItem("Beli barang dapur");
            TodoItem todoItem2 = new TodoItem("Beli buku");

            //save dalam database kene inject repository:
            todoItemRepository.save(todoItem1);
            todoItemRepository.save(todoItem2);
        }

        logger.info("Number of ToDoItems: {}", todoItemRepository.count());
    }
}
