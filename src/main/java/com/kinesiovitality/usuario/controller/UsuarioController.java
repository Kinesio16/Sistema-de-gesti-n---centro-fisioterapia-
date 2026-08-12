package com.kinesiovitality.usuario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.usuario.dto.ActualizarPasswordRequest;
import com.kinesiovitality.usuario.dto.UsuarioRequest;
import com.kinesiovitality.usuario.dto.UsuarioResponse;
import com.kinesiovitality.usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.kinesiovitality.usuario.dto.ActualizarUsuarioRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Tag(
	    name = "Usuarios",
	    description = "Administración de usuarios del sistema."
	)
	@SecurityRequirement(name = "Bearer Authentication")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @Operation(
    	    summary = "Crear usuario",
    	    description = "Registra un nuevo fisioterapeuta en el sistema."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente"),
    	    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    	    @ApiResponse(responseCode = "401", description = "No autenticado"),
    	    @ApiResponse(responseCode = "403", description = "Sin permisos")
    	})
    @PostMapping
    public ResponseEntity<ApiResponseDTO<UsuarioResponse>> crearUsuario(
            @Valid @RequestBody UsuarioRequest request) {

        UsuarioResponse response = usuarioService.crearUsuario(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDTO<>(
                        true,
                        "Usuario creado correctamente.",
                        response,
                        null));
    }
    
    @Operation(
    	    summary = "Listar usuarios",
    	    description = "Obtiene todos los usuarios registrados."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
    	    @ApiResponse(responseCode = "401", description = "No autenticado")
    	})
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<UsuarioResponse>>> listarUsuarios() {

        return ResponseEntity.ok(
                new ApiResponseDTO<>(
                        true,
                        "Listado de usuarios.",
                        usuarioService.listarUsuarios(),
                        null));
    }
    
    @Operation(
    	    summary = "Buscar usuario por ID",
    	    description = "Obtiene la información de un usuario mediante su identificador."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
    	    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    	})
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UsuarioResponse>> buscarPorId(
    		@Parameter(description = "ID del usuario")
    		@PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponseDTO<>(
                        true,
                        "Usuario encontrado.",
                        usuarioService.buscarPorId(id),
                        null));
    }
    
    @Operation(
    	    summary = "Actualizar usuario",
    	    description = "Actualiza la información de un usuario."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
    	    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    	    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    	})
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UsuarioResponse>> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarUsuarioRequest request) {

        return ResponseEntity.ok(
                new ApiResponseDTO<>(
                        true,
                        "Usuario actualizado.",
                        usuarioService.actualizarUsuario(id, request),
                        null));
    }
    
    @Operation(
    	    summary = "Activar usuario",
    	    description = "Activa un usuario previamente deshabilitado."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Usuario activado")
    	})
    @PatchMapping("/{id}/activar")
    public ResponseEntity<ApiResponseDTO<String>> activarUsuario(
            @PathVariable Long id) {

        usuarioService.activarUsuario(id);

        return ResponseEntity.ok(
                new ApiResponseDTO<>(
                        true,
                        "Usuario activado.",
                        null,
                        null));
    }
    
    @Operation(
    	    summary = "Desactivar usuario",
    	    description = "Desactiva un usuario del sistema."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Usuario desactivado")
    	})
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<ApiResponseDTO<String>> desactivarUsuario(
            @PathVariable Long id) {

        usuarioService.desactivarUsuario(id);

        return ResponseEntity.ok(
                new ApiResponseDTO<>(
                        true,
                        "Usuario desactivado.",
                        null,
                        null));
    }
    
    @Operation(
    	    summary = "Cambiar contraseña",
    	    description = "Actualiza la contraseña de un usuario."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Contraseña actualizada"),
    	    @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
    	    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    	})
    @PatchMapping("/{id}/password")
    public ResponseEntity<ApiResponseDTO<String>> cambiarPassword(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarPasswordRequest request) {

        usuarioService.cambiarPassword(id, request);

        return ResponseEntity.ok(
                new ApiResponseDTO<>(
                        true,
                        "Contraseña actualizada.",
                        null,
                        null));
    }

}