package com.kinesiovitality.common.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kinesiovitality.common.response.ApiResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===============================
    // Validaciones (@Valid)
    // ===============================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Map<String, String>>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage()));

        ApiResponseDTO<Map<String, String>> response = new ApiResponseDTO<>();
        response.setSuccess(false);
        response.setMessage("Error de validación.");
        response.setData(errores);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.badRequest().body(response);
    }

    // ===============================
    // Regla de negocio
    // ===============================
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleIllegalArgument(
            IllegalArgumentException ex) {

        ApiResponseDTO<Object> response = new ApiResponseDTO<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        response.setData(null);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.badRequest().body(response);
    }

    // ===============================
    // Recurso no encontrado
    // ===============================
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleNotFound(
            ResourceNotFoundException ex) {

        ApiResponseDTO<Object> response = new ApiResponseDTO<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        response.setData(null);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // ===============================
    // Error inesperado
    // ===============================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleException(Exception ex) {

        ex.printStackTrace();

        ApiResponseDTO<Object> response = new ApiResponseDTO<>();
        response.setSuccess(false);
        response.setMessage("Ha ocurrido un error interno en el servidor.");
        response.setData(null);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}