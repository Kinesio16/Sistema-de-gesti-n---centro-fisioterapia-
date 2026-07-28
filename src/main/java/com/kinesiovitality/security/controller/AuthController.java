package com.kinesiovitality.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.security.dto.AuthResponse;
import com.kinesiovitality.security.dto.LoginRequest;
import com.kinesiovitality.security.jwt.JwtService;
import com.kinesiovitality.usuario.model.Usuario;
import com.kinesiovitality.usuario.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthController(
            AuthenticationManager authenticationManager,
            UsuarioRepository usuarioRepository,
            JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
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
}