package com.kinesiovitality.usuario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.usuario.dto.ActualizarPasswordRequest;
import com.kinesiovitality.usuario.dto.UsuarioRequest;
import com.kinesiovitality.usuario.dto.UsuarioResponse;
import com.kinesiovitality.usuario.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponse>> crearUsuario(
            @Valid @RequestBody UsuarioRequest request) {

        UsuarioResponse response = usuarioService.crearUsuario(request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Usuario creado correctamente.",
                        response,
                        null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponse>>> listarUsuarios() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Listado de usuarios.",
                        usuarioService.listarUsuarios(),
                        null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponse>> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Usuario encontrado.",
                        usuarioService.buscarPorId(id),
                        null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponse>> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Usuario actualizado.",
                        usuarioService.actualizarUsuario(id, request),
                        null));
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<ApiResponse<String>> activarUsuario(
            @PathVariable Long id) {

        usuarioService.activarUsuario(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Usuario activado.",
                        null,
                        null));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<ApiResponse<String>> desactivarUsuario(
            @PathVariable Long id) {

        usuarioService.desactivarUsuario(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Usuario desactivado.",
                        null,
                        null));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<ApiResponse<String>> cambiarPassword(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarPasswordRequest request) {

        usuarioService.cambiarPassword(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Contraseña actualizada.",
                        null,
                        null));
    }

}