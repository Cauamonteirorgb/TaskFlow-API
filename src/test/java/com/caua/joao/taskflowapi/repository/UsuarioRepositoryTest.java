package com.caua.joao.taskflowapi.repository;

import com.caua.joao.taskflowapi.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    void deveSalvarUsuario() {

        Usuario usuario = new Usuario();

        usuario.setNome("Cauã");
        usuario.setEmail("caua@email.com");

        Usuario salvo = repository.save(usuario);

        assertTrue(salvo.getId() != null);
        assertEquals("Cauã", salvo.getNome());
    }

    @Test
    void deveBuscarUsuarioPorId() {

        Usuario usuario = new Usuario();

        usuario.setNome("João");
        usuario.setEmail("joao@email.com");

        Usuario salvo = repository.save(usuario);

        Optional<Usuario> resultado =
                repository.findById(salvo.getId());

        assertTrue(resultado.isPresent());
        assertEquals(
                "João",
                resultado.get().getNome()
        );
    }
}