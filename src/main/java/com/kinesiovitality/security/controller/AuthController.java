package com.kinesiovitality.security.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.security.blacklist.service.TokenBlacklistService;
import com.kinesiovitality.security.dto.AuthResponse;
import com.kinesiovitality.security.dto.LoginRequest;
import com.kinesiovitality.security.jwt.JwtService;
import com.kinesiovitality.usuario.model.Usuario;
import com.kinesiovitality.usuario.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(
            AuthenticationManager authenticationManager,
            UsuarioRepository usuarioRepository,
            JwtService jwtService,
            TokenBlacklistService tokenBlacklistService) {

        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        String username = authentication.getName();

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow();

        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", usuario.getRol().name());

        String token = jwtService.generateToken(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.UserDetails userDetails
                ? userDetails
                : null, claims);

        return new AuthResponse(
                token,
                usuario.getUsername(),
                usuario.getRol().name());
    }
    
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            false,
                            "No se envió un token válido.",
                            null));

        }

        String token = authHeader.substring(7);

        tokenBlacklistService.blacklistToken(
                token,
                jwtService.extractExpirationAsLocalDateTime(token),
                "Logout");

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Sesión cerrada correctamente.",
                        null));
    }
}