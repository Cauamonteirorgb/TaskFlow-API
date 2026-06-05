package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.entity.Status;
import com.caua.joao.taskflowapi.service.StatusService;
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

@WebMvcTest(StatusController.class)
class StatusControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StatusService service;

    @Test
    void deveListarTodosOsStatus() throws Exception {
        Status s1 = new Status(); s1.setId(1L); s1.setNome("Pendente");
        Status s2 = new Status(); s2.setId(2L); s2.setNome("Concluído");
        when(service.listarTodos()).thenReturn(List.of(s1, s2));

        mockMvc.perform(get("/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Pendente"))
                .andExpect(jsonPath("$[1].nome").value("Concluído"));
    }

    @Test
    void deveBuscarStatusPorId() throws Exception {
        Status s = new Status(); s.setId(1L); s.setNome("Em andamento");
        when(service.buscarPorId(1L)).thenReturn(s);

        mockMvc.perform(get("/status/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Em andamento"));
    }

    @Test
    void deveCriarStatus() throws Exception {
        Status s = new Status(); s.setId(1L); s.setNome("Bloqueado");
        when(service.salvar(any())).thenReturn(s);

        mockMvc.perform(post("/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(s)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Bloqueado"));
    }

    @Test
    void deveAtualizarStatus() throws Exception {
        Status s = new Status(); s.setId(1L); s.setNome("Nome novo");
        when(service.atualizar(eq(1L), any())).thenReturn(s);

        mockMvc.perform(put("/status/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(s)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nome novo"));
    }

    @Test
    void deveDeletarStatus() throws Exception {
        doNothing().when(service).deletar(1L);

        mockMvc.perform(delete("/status/1"))
                .andExpect(status().isNoContent());
    }
}