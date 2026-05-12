package com.caua.joao.taskflowapi.repository;

import com.caua.joao.taskflowapi.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}