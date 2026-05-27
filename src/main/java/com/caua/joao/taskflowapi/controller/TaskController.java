package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.dto.TaskRequestDTO;
import com.caua.joao.taskflowapi.dto.TaskResponseDTO;
import com.caua.joao.taskflowapi.entity.Task;
import com.caua.joao.taskflowapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(
        name = "Tasks",
        description = "Endpoints para gerenciamento de tarefas"
)
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas as tarefas")
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listarTasks() {
        List<TaskResponseDTO> resposta = new ArrayList<>();

        service.listarTodos().forEach(task ->
                resposta.add(TaskResponseDTO.from(task))
        );

        return ResponseEntity.ok(resposta);
    }

    @Operation(summary = "Buscar tarefa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> buscarTask(@PathVariable Long id) {
        Task task = service.buscarPorId(id);
        return ResponseEntity.ok(TaskResponseDTO.from(task));
    }

    @Operation(summary = "Criar nova tarefa")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> criarTask(
            @Valid @RequestBody TaskRequestDTO dto
    ) {
        Task task = new Task();
        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());

        Task salva = service.salvar(task);

        return ResponseEntity.status(201).body(TaskResponseDTO.from(salva));
    }

    @Operation(summary = "Atualizar tarefa por ID")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> atualizarTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO dto
    ) {
        Task task = new Task();
        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());

        Task atualizada = service.atualizar(id, task);

        return ResponseEntity.ok(TaskResponseDTO.from(atualizada));
    }

    @Operation(summary = "Deletar tarefa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTask(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}