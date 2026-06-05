package com.caua.joao.taskflowapi.service;

import com.caua.joao.taskflowapi.entity.Usuario;
import com.caua.joao.taskflowapi.exception.ResourceNotFoundException;
import com.caua.joao.taskflowapi.repository.UsuarioRepository;
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
class UsuarioServiceTest {

    @Mock
    UsuarioRepository repository;

    @InjectMocks
    UsuarioService service;

    @Test
    void deveListarTodosOsUsuarios() {
        Usuario u1 = new Usuario(); u1.setNome("João");
        Usuario u2 = new Usuario(); u2.setNome("Maria");
        when(repository.findAll()).thenReturn(List.of(u1, u2));

        Iterable<Usuario> resultado = service.listarTodos();

        assertNotNull(resultado);
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveBuscarUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João");
        usuario.setEmail("joao@email.com");
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = service.buscarPorId(1L);

        assertEquals("João", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.buscarPorId(99L);
        });
    }

    @Test
    void deveSalvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos");
        usuario.setEmail("carlos@email.com");
        when(repository.save(usuario)).thenReturn(usuario);

        Usuario salvo = service.salvar(usuario);

        assertEquals("Carlos", salvo.getNome());
        verify(repository, times(1)).save(usuario);
    }

    @Test
    void deveAtualizarUsuario() {
        Usuario existente = new Usuario();
        existente.setId(1L);
        existente.setNome("Nome antigo");
        existente.setEmail("antigo@email.com");

        Usuario dados = new Usuario();
        dados.setNome("Nome novo");
        dados.setEmail("novo@email.com");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any())).thenReturn(existente);

        Usuario atualizado = service.atualizar(1L, dados);

        assertEquals("Nome novo", atualizado.getNome());
        assertEquals("novo@email.com", atualizado.getEmail());
    }

    @Test
    void deveDeletarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        service.deletar(1L);

        verify(repository, times(1)).delete(usuario);
    }
}
