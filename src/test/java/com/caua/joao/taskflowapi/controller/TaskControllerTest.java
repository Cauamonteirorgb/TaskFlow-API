package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.entity.Task;
import com.caua.joao.taskflowapi.exception.ResourceNotFoundException;
import com.caua.joao.taskflowapi.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService service;

    @Test
    void deveRetornar404QuandoTaskNaoExistir() throws Exception {

        when(service.buscarPorId(99L))
                .thenThrow(new ResourceNotFoundException("Task", 99L));

        mvc.perform(get("/tasks/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveCriarTaskComSucesso() throws Exception {

        Task taskSalva = new Task();

        taskSalva.setId(1L);
        taskSalva.setTitulo("Estudar Spring Boot");
        taskSalva.setDescricao("Testar POST com MockMvc");

        when(service.salvar(any(Task.class)))
                .thenReturn(taskSalva);

        String json = """
                {
                    "titulo": "Estudar Spring Boot",
                    "descricao": "Testar POST com MockMvc"
                }
                """;

        mvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }
}
