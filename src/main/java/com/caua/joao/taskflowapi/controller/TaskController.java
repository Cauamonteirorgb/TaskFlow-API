package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.entity.Task;
import com.caua.joao.taskflowapi.repository.TaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public Iterable<Task> listarTasks() {
        return taskRepository.findAll();
    }
}