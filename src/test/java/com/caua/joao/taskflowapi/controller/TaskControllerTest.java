package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.dto.TaskRequestDTO;
import com.caua.joao.taskflowapi.entity.Task;
import com.caua.joao.taskflowapi.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TaskService service;

    @Test
    void deveListarTodasAsTasks() throws Exception {
        Task t1 = new Task(); t1.setId(1L); t1.setTitulo("Task 1");
        Task t2 = new Task(); t2.setId(2L); t2.setTitulo("Task 2");
        when(service.listarTodos()).thenReturn(List.of(t1, t2));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Task 1"))
                .andExpect(jsonPath("$[1].titulo").value("Task 2"));
    }

    @Test
    void deveBuscarTaskPorId() throws Exception {
        Task task = new Task(); task.setId(1L); task.setTitulo("Estudar Spring");
        when(service.buscarPorId(1L)).thenReturn(task);

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Estudar Spring"));
    }

    @Test
    void deveCriarTask() throws Exception {
        TaskRequestDTO dto = new TaskRequestDTO("Nova Task", "Descrição");
        Task task = new Task(); task.setId(1L); task.setTitulo("Nova Task"); task.setDescricao("Descrição");
        when(service.salvar(any())).thenReturn(task);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Nova Task"));
    }

    @Test
    void deveAtualizarTask() throws Exception {
        TaskRequestDTO dto = new TaskRequestDTO("Titulo atualizado", "Nova desc");
        Task task = new Task(); task.setId(1L); task.setTitulo("Titulo atualizado");
        when(service.atualizar(eq(1L), any())).thenReturn(task);

        mockMvc.perform(put("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Titulo atualizado"));
    }

    @Test
    void deveDeletarTask() throws Exception {
        doNothing().when(service).deletar(1L);

        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isNoContent());
    }
}
