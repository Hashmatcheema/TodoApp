package com.example.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    private final TaskRepository taskRepository;

    @Autowired
    public TasksService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Create a new task in the database
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Retrieve all tasks from the database
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Retrieve a single task by its ID
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    // Update an existing task
    public Optional<Task> updateTask(String id, Task taskDetails) {
        return taskRepository.findById(id);
    }

    // Delete a task by its ID
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
