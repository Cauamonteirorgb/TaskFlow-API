package com.caua.joao.taskflowapi.repository;

import com.caua.joao.taskflowapi.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository repository;

    @Test
    void deveSalvarTask() {

        Task task = new Task();

        task.setTitulo("Estudar Spring Boot");
        task.setDescricao("Aprender testes");

        Task salva = repository.save(task);

        Optional<Task> resultado = repository.findById(salva.getId());

        assertTrue(resultado.isPresent());
    }

    @Test
void deveBuscarTaskPorId() {
    Task task = new Task();
    task.setTitulo("Estudar DTO");
    task.setDescricao("Semana 4");

    Task salva = repository.save(task);

    Optional<Task> resultado = repository.findById(salva.getId());

    assertTrue(resultado.isPresent());
}
}