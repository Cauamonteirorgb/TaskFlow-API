package com.caua.joao.taskflowapi.service;

import com.caua.joao.taskflowapi.entity.Status;
import com.caua.joao.taskflowapi.exception.ResourceNotFoundException;
import com.caua.joao.taskflowapi.repository.StatusRepository;
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
class StatusServiceTest {

    @Mock
    StatusRepository repository;

    @InjectMocks
    StatusService service;

    @Test
    void deveListarTodosOsStatus() {
        Status s1 = new Status(); s1.setNome("Pendente");
        Status s2 = new Status(); s2.setNome("Concluído");
        when(repository.findAll()).thenReturn(List.of(s1, s2));

        Iterable<Status> resultado = service.listarTodos();

        assertNotNull(resultado);
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveBuscarStatusPorId() {
        Status status = new Status();
        status.setId(1L);
        status.setNome("Em andamento");
        when(repository.findById(1L)).thenReturn(Optional.of(status));

        Status resultado = service.buscarPorId(1L);

        assertEquals("Em andamento", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99L);
        });
    }

    @Test
    void deveSalvarStatus() {
        Status status = new Status();
        status.setNome("Bloqueado");
        when(repository.save(status)).thenReturn(status);

        Status salvo = service.salvar(status);

        assertEquals("Bloqueado", salvo.getNome());
        verify(repository, times(1)).save(status);
    }

    @Test
    void deveAtualizarStatus() {
        Status existente = new Status();
        existente.setId(1L);
        existente.setNome("Nome antigo");

        Status dados = new Status();
        dados.setNome("Nome novo");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any())).thenReturn(existente);

        Status atualizado = service.atualizar(1L, dados);

        assertEquals("Nome novo", atualizado.getNome());
    }

    @Test
    void deveDeletarStatus() {
        Status status = new Status();
        status.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(status));

        service.deletar(1L);

        verify(repository, times(1)).delete(status);
    }
}
