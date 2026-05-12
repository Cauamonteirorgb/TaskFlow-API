package com.caua.joao.taskflowapi.controller;

import com.caua.joao.taskflowapi.entity.Usuario;
import com.caua.joao.taskflowapi.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuarios")
    public Iterable<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}