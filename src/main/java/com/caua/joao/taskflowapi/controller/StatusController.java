package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.entity.Status;
import com.caua.joao.taskflowapi.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusService service;

    public StatusController(StatusService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Status>> listarStatus() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> buscarStatus(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Status> criarStatus(
            @Valid @RequestBody Status status) {

        Status salvo = service.salvar(status);

        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody Status status) {

        return ResponseEntity.ok(service.atualizar(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarStatus(
            @PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}