package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.entity.Usuario;
import com.caua.joao.taskflowapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(
            @Valid @RequestBody Usuario usuario) {

        Usuario salvo = service.salvar(usuario);

        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
public ResponseEntity<Usuario> atualizarUsuario(
        @PathVariable Long id,
        @Valid @RequestBody Usuario usuario) {

    return ResponseEntity.ok(service.atualizar(id, usuario));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletarUsuario(
        @PathVariable Long id) {

    service.deletar(id);

    return ResponseEntity.noContent().build();
}
}