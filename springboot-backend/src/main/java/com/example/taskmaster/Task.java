package com.example.taskmaster;

import org.springframework.data.annotation.Id;

@Document
public class Task {
    @Id
    private String id;
    private String description;

    public long getTitle(Object description) {
        return 0;
    }

    public Object getDescription() {
        return null;
    }

    public Object isCompleted() {
        return null;
    }
    // Other fields, getters, and setters
}
