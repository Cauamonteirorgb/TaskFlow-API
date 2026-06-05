package com.caua.joao.taskflowapi.service;

import com.caua.joao.taskflowapi.entity.Categoria;
import com.caua.joao.taskflowapi.exception.ResourceNotFoundException;
import com.caua.joao.taskflowapi.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    CategoriaRepository repository;

    @InjectMocks
    CategoriaService service;

    @Test
    void deveListarTodasAsCategorias() {
        Categoria c1 = new Categoria(); c1.setNome("Trabalho");
        Categoria c2 = new Categoria(); c2.setNome("Pessoal");
        when(repository.findAll()).thenReturn(List.of(c1, c2));

        Iterable<Categoria> resultado = service.listarTodos();

        assertNotNull(resultado);
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Trabalho");
        when(repository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria resultado = service.buscarPorId(1L);

        assertEquals("Trabalho", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99L);
        });
    }

    @Test
    void deveSalvarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Estudos");
        when(repository.save(categoria)).thenReturn(categoria);

        Categoria salva = service.salvar(categoria);

        assertEquals("Estudos", salva.getNome());
        verify(repository, times(1)).save(categoria);
    }

    @Test
    void deveAtualizarCategoria() {
        Categoria existente = new Categoria();
        existente.setId(1L);
        existente.setNome("Nome antigo");

        Categoria dados = new Categoria();
        dados.setNome("Nome novo");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any())).thenReturn(existente);

        Categoria atualizada = service.atualizar(1L, dados);

        assertEquals("Nome novo", atualizada.getNome());
    }

    @Test
    void deveDeletarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(categoria));

        service.deletar(1L);

        verify(repository, times(1)).delete(categoria);
    }
}
