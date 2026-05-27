package com.caua.joao.taskflowapi.service;

import com.caua.joao.taskflowapi.entity.Categoria;
import com.caua.joao.taskflowapi.exception.ResourceNotFoundException;
import com.caua.joao.taskflowapi.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    @Transactional(readOnly = true)
    public Iterable<Categoria> listarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria", id));
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Transactional
    public Categoria atualizar(Long id, Categoria dados) {

        Categoria categoria = buscarPorId(id);

        categoria.setNome(dados.getNome());

        return repository.save(categoria);
    }

    @Transactional
    public void deletar(Long id) {

        Categoria categoria = buscarPorId(id);

        repository.delete(categoria);
    }
}