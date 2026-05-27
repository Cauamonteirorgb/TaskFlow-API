package com.caua.joao.taskflowapi.dto;

import com.caua.joao.taskflowapi.entity.Task;

public record TaskResponseDTO(
        Long id,
        String titulo,
        String descricao
) {
    public static TaskResponseDTO from(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitulo(),
                task.getDescricao()
        );
    }
}