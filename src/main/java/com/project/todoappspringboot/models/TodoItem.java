package com.project.todoappspringboot.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "todo_item")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Boolean complete;

    //Setup time and date:
    @Getter
    @Setter
    private Instant createdDate;

    @Getter
    @Setter
    private Instant modifiedDate;

    public TodoItem() {
    }

    public TodoItem(String description) {
        this.description = description;
        this.complete = false;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }

    @Override
    public String toString(){
        return String.format("TodoItem{id=%d, description='%s', complete='%s', createdDate='%s', modifiedDate='%s'}",
                id, description, complete, createdDate, modifiedDate);
    }




}
