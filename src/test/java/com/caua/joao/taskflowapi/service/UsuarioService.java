package com.caua.joao.taskflowapi.service;

import com.caua.joao.taskflowapi.entity.Usuario;
import com.caua.joao.taskflowapi.exception.ResourceNotFoundException;
import com.caua.joao.taskflowapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    @Transactional(readOnly = true)
    public Iterable<Usuario> listarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário", id));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dados) {

        Usuario usuario = buscarPorId(id);

        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());

        return repository.save(usuario);
    }

    @Transactional
    public void deletar(Long id) {

        Usuario usuario = buscarPorId(id);

        repository.delete(usuario);
    }
}