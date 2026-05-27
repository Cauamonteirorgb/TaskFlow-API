package com.caua.joao.taskflowapi.repository;

import com.caua.joao.taskflowapi.entity.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;

    @Test
    void deveSalvarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Estudos");

        Categoria salva = repository.save(categoria);

        assertTrue(salva.getId() != null);
        assertEquals("Estudos", salva.getNome());
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Categoria categoria = new Categoria();
        categoria.setNome("Trabalho");

        Categoria salva = repository.save(categoria);

        Optional<Categoria> resultado = repository.findById(salva.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Trabalho", resultado.get().getNome());
    }
}