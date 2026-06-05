package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.entity.Usuario;
import com.caua.joao.taskflowapi.service.UsuarioService;
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

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UsuarioService service;

    @Test
    void deveListarTodosOsUsuarios() throws Exception {
        Usuario u1 = new Usuario(); u1.setId(1L); u1.setNome("João"); u1.setEmail("joao@email.com");
        Usuario u2 = new Usuario(); u2.setId(2L); u2.setNome("Maria"); u2.setEmail("maria@email.com");
        when(service.listarTodos()).thenReturn(List.of(u1, u2));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[1].nome").value("Maria"));
    }

    @Test
    void deveBuscarUsuarioPorId() throws Exception {
        Usuario u = new Usuario(); u.setId(1L); u.setNome("João"); u.setEmail("joao@email.com");
        when(service.buscarPorId(1L)).thenReturn(u);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    void deveCriarUsuario() throws Exception {
        Usuario u = new Usuario(); u.setId(1L); u.setNome("Carlos"); u.setEmail("carlos@email.com");
        when(service.salvar(any())).thenReturn(u);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Carlos"));
    }

    @Test
    void deveAtualizarUsuario() throws Exception {
        Usuario u = new Usuario(); u.setId(1L); u.setNome("Nome novo"); u.setEmail("novo@email.com");
        when(service.atualizar(eq(1L), any())).thenReturn(u);

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nome novo"));
    }

    @Test
    void deveDeletarUsuario() throws Exception {
        doNothing().when(service).deletar(1L);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }
}