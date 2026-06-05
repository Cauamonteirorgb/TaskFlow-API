package com.caua.joao.taskflowapi.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    GlobalExceptionHandler handler;

    @Test
    void deveRetornar404QuandoResourceNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Task", 99L);

        ResponseEntity<ErrorResponse> resposta = handler.notFound(ex);

        assertEquals(404, resposta.getStatusCode().value());
        assertEquals(404, resposta.getBody().status());
        assertTrue(resposta.getBody().message().contains("99"));
    }

    @Test
    void deveRetornar400QuandoValidacaoFalha() {
        FieldError fieldError = new FieldError("task", "titulo", "Título obrigatório");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ErrorResponse> resposta = handler.validation(ex);

        assertEquals(400, resposta.getStatusCode().value());
        assertEquals(400, resposta.getBody().status());
        assertTrue(resposta.getBody().message().contains("titulo"));
        assertTrue(resposta.getBody().message().contains("Título obrigatório"));
    }

    @Test
    void deveRetornarMensagemCorretaNoResourceNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Categoria", 5L);

        ResponseEntity<ErrorResponse> resposta = handler.notFound(ex);

        assertTrue(resposta.getBody().message().contains("Categoria"));
        assertTrue(resposta.getBody().message().contains("5"));
    }

    @Test
    void errorResponseDeveTerTimestamp() {
        ErrorResponse error = new ErrorResponse(404, "não encontrado");

        assertNotNull(error.timestamp());
        assertEquals(404, error.status());
        assertEquals("não encontrado", error.message());
    }

    @Test
    void resourceNotFoundDeveTerMensagemCorreta() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Usuario", 10L);

        assertEquals("Usuario não encontrado: 10", ex.getMessage());
    }
}