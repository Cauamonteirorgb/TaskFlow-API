package com.caua.joao.taskflowapi.service;

import com.caua.joao.taskflowapi.entity.Task;
import com.caua.joao.taskflowapi.exception.ResourceNotFoundException;
import com.caua.joao.taskflowapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    @Transactional(readOnly = true)
    public Iterable<Task> listarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Task buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task", id));
    }

    @Transactional
    public Task salvar(Task task) {
        return repository.save(task);
    }

    @Transactional
    public Task atualizar(Long id, Task dados) {

        Task task = buscarPorId(id);

        task.setTitulo(dados.getTitulo());
        task.setDescricao(dados.getDescricao());
        task.setStatus(dados.getStatus());
        task.setCategoria(dados.getCategoria());
        task.setUsuario(dados.getUsuario());

        return repository.save(task);
    }

    @Transactional
    public void deletar(Long id) {

        Task task = buscarPorId(id);

        repository.delete(task);
    }
}