package com.caua.joao.taskflowapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO(
        @NotBlank(message = "Título obrigatório")
        @Size(min = 3, max = 200)
        String titulo,

        @Size(max = 500)
        String descricao
) {
}