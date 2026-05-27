package com.caua.joao.taskflowapi.service;

import com.caua.joao.taskflowapi.entity.Status;
import com.caua.joao.taskflowapi.exception.ResourceNotFoundException;
import com.caua.joao.taskflowapi.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository repository;

    @Transactional(readOnly = true)
    public Iterable<Status> listarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Status buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Status", id));
    }

    @Transactional
    public Status salvar(Status status) {
        return repository.save(status);
    }

    @Transactional
    public Status atualizar(Long id, Status dados) {

        Status status = buscarPorId(id);

        status.setNome(dados.getNome());

        return repository.save(status);
    }

    @Transactional
    public void deletar(Long id) {

        Status status = buscarPorId(id);

        repository.delete(status);
    }
}