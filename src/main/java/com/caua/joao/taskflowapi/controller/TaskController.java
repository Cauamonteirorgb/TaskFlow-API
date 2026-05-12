package com.caua.joao.taskflowapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping("/")
    public String home() {
        return "TaskFlow API está funcionando!";
    }

    @GetMapping("/tasks")
    public String listarTasks() {
        return "API funcionando!";
    }
}