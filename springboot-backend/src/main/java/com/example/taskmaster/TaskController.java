package com.example.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TasksService tasksService;

    @Autowired
    public TaskController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    // POST endpoint to create a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return tasksService.createTask(task);
    }

    // GET endpoint to retrieve all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return tasksService.getAllTasks();
    }

    // GET endpoint to retrieve a task by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable String id) {

        return tasksService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT endpoint to update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable String id, @RequestBody Task taskDetails) {
        return tasksService.updateTask(id, taskDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE endpoint to delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id) {
        if (tasksService.deleteTask(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Additional CRUD endpoints can be added here...
}
