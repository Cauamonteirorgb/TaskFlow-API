package com.caua.joao.taskflowapi.repository;

import com.caua.joao.taskflowapi.entity.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class StatusRepositoryTest {

    @Autowired
    private StatusRepository repository;

    @Test
    void deveSalvarStatus() {
        Status status = new Status();
        status.setNome("Pendente");

        Status salvo = repository.save(status);

        assertTrue(salvo.getId() != null);
        assertEquals("Pendente", salvo.getNome());
    }

    @Test
    void deveBuscarStatusPorId() {
        Status status = new Status();
        status.setNome("Concluído");

        Status salvo = repository.save(status);

        Optional<Status> resultado = repository.findById(salvo.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Concluído", resultado.get().getNome());
    }
}