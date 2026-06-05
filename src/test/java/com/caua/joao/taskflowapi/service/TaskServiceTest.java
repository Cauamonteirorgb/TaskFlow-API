package com.caua.joao.taskflowapi.service;

import com.caua.joao.taskflowapi.entity.Task;
import com.caua.joao.taskflowapi.exception.ResourceNotFoundException;
import com.caua.joao.taskflowapi.repository.TaskRepository;
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
class TaskServiceTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    TaskService service;

    @Test
    void deveListarTodasAsTasks() {
        Task t1 = new Task(); t1.setTitulo("Task 1");
        Task t2 = new Task(); t2.setTitulo("Task 2");
        when(repository.findAll()).thenReturn(List.of(t1, t2));

        Iterable<Task> resultado = service.listarTodos();

        assertNotNull(resultado);
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveBuscarTaskPorId() {
        Task task = new Task();
        task.setId(1L);
        task.setTitulo("Estudar Spring");
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        Task resultado = service.buscarPorId(1L);

        assertEquals("Estudar Spring", resultado.getTitulo());
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99L);
        });
    }

    @Test
    void deveSalvarTask() {
        Task task = new Task();
        task.setTitulo("Nova task");
        when(repository.save(task)).thenReturn(task);

        Task salva = service.salvar(task);

        assertEquals("Nova task", salva.getTitulo());
        verify(repository, times(1)).save(task);
    }

    @Test
    void deveDeletarTask() {
        Task task = new Task();
        task.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        service.deletar(1L);

        verify(repository, times(1)).delete(task);
    }

    @Test
    void deveAtualizarTask() {
        Task existente = new Task();
        existente.setId(1L);
        existente.setTitulo("Titulo antigo");

        Task dados = new Task();
        dados.setTitulo("Titulo novo");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any())).thenReturn(existente);

        Task atualizada = service.atualizar(1L, dados);

        assertEquals("Titulo novo", atualizada.getTitulo());
    }
}
