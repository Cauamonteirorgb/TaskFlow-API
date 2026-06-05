package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.entity.Categoria;
import com.caua.joao.taskflowapi.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @Mock
    CategoriaService service;

    @InjectMocks
    CategoriaController controller;

    @Test
    void deveListarTodasAsCategorias() {
        Categoria c1 = new Categoria(); c1.setNome("Trabalho");
        Categoria c2 = new Categoria(); c2.setNome("Pessoal");
        when(service.listarTodos()).thenReturn(List.of(c1, c2));

        ResponseEntity<Iterable<Categoria>> resposta = controller.listarCategorias();

        assertEquals(200, resposta.getStatusCode().value());
        assertNotNull(resposta.getBody());
        verify(service, times(1)).listarTodos();
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Trabalho");
        when(service.buscarPorId(1L)).thenReturn(categoria);

        ResponseEntity<Categoria> resposta = controller.buscarCategoria(1L);

        assertEquals(200, resposta.getStatusCode().value());
        assertEquals("Trabalho", resposta.getBody().getNome());
    }

    @Test
    void deveCriarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Estudos");
        when(service.salvar(any())).thenReturn(categoria);

        ResponseEntity<Categoria> resposta = controller.criarCategoria(categoria);

        assertEquals(201, resposta.getStatusCode().value());
        assertEquals("Estudos", resposta.getBody().getNome());
    }

    @Test
    void deveAtualizarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Nome novo");
        when(service.atualizar(eq(1L), any())).thenReturn(categoria);

        ResponseEntity<Categoria> resposta = controller.atualizarCategoria(1L, categoria);

        assertEquals(200, resposta.getStatusCode().value());
        assertEquals("Nome novo", resposta.getBody().getNome());
    }

    @Test
    void deveDeletarCategoria() {
        doNothing().when(service).deletar(1L);

        ResponseEntity<Void> resposta = controller.deletarCategoria(1L);

        assertEquals(204, resposta.getStatusCode().value());
        verify(service, times(1)).deletar(1L);
    }
}