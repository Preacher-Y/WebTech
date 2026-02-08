package com.example.question5_task_mangement_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.question5_task_mangement_api.model.Task;



@RestController
@RequestMapping("/api/tasks")
public class TaskManagerAPi {
    List<Task> tasks = new ArrayList<>();

    public TaskManagerAPi(){
        tasks.add(new Task(1, "Finish REST API", "Complete all task endpoints", false, "HIGH", "2026-02-10"));
        tasks.add(new Task(2, "Write unit tests", "Add tests for TaskController", false, "MEDIUM", "2026-02-12"));
        tasks.add(new Task(3, "Fix validation bugs", "Handle invalid priority and dates", true, "HIGH", "2026-02-05"));
        tasks.add(new Task(4, "Refactor code", "Clean up controller and helpers", false, "MEDIUM", "2026-02-15"));
        tasks.add(new Task(5, "Update README", "Document API endpoints", true, "LOW", "2026-02-03"));
        tasks.add(new Task(6, "Design database schema", "Prepare Task table structure", false, "HIGH", "2026-02-20"));
        tasks.add(new Task(7, "Add pagination", "Paginate GET /api/tasks", false, "MEDIUM", "2026-02-18"));
        tasks.add(new Task(8, "Improve error handling", "Standardize error responses", true, "LOW", "2026-02-01"));
        tasks.add(new Task(9, "Integrate frontend", "Connect React app to API", false, "HIGH", "2026-02-25"));
        tasks.add(new Task(10, "Deploy application", "Deploy API to staging environment", false, "HIGH", "2026-03-01"));

    }

    @GetMapping
    public List<Task> getMethodName() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable long id) {
        return tasks.stream()
                .filter(t ->t.getTaskId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Task with ID " + id + " not found"
                ));
    }

    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam boolean completed) {
        return tasks.stream()
                .filter(t -> t.isCompleted() == completed)
                .collect(Collectors.toList());
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getTasksByPriority(@PathVariable String priority) {
        return tasks.stream()
                .filter(t -> t.getPriority().equalsIgnoreCase(priority))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task newTask) {
        if(!newTask.getPriority().equalsIgnoreCase("LOW")
            && !newTask.getPriority().equalsIgnoreCase("MEDIUM")
            && !newTask.getPriority().equalsIgnoreCase("HIGH")){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Priority Must Be LOW, MEDIUM or HIGH");
            }
        tasks.add(newTask);
        return newTask;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody Task updateTask) {
        Task existing = tasks.stream()
                .filter(t ->t.getTaskId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Task with ID " + id + " not found"
                ));
        if(!updateTask.getPriority().equalsIgnoreCase("LOW")
            && !updateTask.getPriority().equalsIgnoreCase("MEDIUM")
            && !updateTask.getPriority().equalsIgnoreCase("HIGH")){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Priority Must Be LOW, MEDIUM or HIGH");
            }

        existing.setTitle(updateTask.getTitle().trim());
        existing.setDescription(updateTask.getDescription());
        existing.setCompleted(updateTask.isCompleted());
        existing.setPriority(updateTask.getPriority().toUpperCase());
        existing.setDueDate(updateTask.getDueDate());

        return existing;
    }

    @PatchMapping("/{id}/complete")
    public Task markTaskCompleted(@PathVariable long id) {
        Task existing = tasks.stream()
                .filter(t ->t.getTaskId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Task with ID " + id + " not found"
                ));
        if (existing.isCompleted()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"The Task is Already Completed");
        }
        existing.setCompleted(true);
        return existing;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteTask(@PathVariable long id) {
        Task existing = tasks.stream()
                    .filter(t ->t.getTaskId() == id)
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Task with ID " + id + " not found"
                    ));
        tasks.remove(existing);

        return Map.of(
            "message", "Task deleted successfully",
            "taskId", String.valueOf(id)
        );
    }
}
