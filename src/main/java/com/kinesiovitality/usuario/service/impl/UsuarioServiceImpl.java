package com.kinesiovitality.usuario.service.impl;

import java.util.List;  
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.Rol;
import com.kinesiovitality.usuario.dto.ActualizarPasswordRequest;
import com.kinesiovitality.usuario.dto.UsuarioRequest;
import com.kinesiovitality.usuario.dto.UsuarioResponse;
import com.kinesiovitality.usuario.model.Usuario;
import com.kinesiovitality.usuario.repository.UsuarioRepository;
import com.kinesiovitality.usuario.service.UsuarioService;
import com.kinesiovitality.usuario.dto.ActualizarUsuarioRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
 
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    private UsuarioResponse convertirRespuesta(Usuario usuario) {

        UsuarioResponse response = new UsuarioResponse();

        response.setId(usuario.getId());
        response.setUsername(usuario.getUsername());
        response.setRol(usuario.getRol().name());
        response.setActivo(usuario.getActivo());
        response.setDebeCambiarPassword(usuario.getDebeCambiarPassword());
        response.setFechaCreacion(usuario.getFechaCreacion());

        return response;
    }
    
    @Override
    public List<UsuarioResponse> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .collect(Collectors.toList());

    }
    
    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {

        if (usuarioRepository.existsByUsername(request.getUsername())) {
        	throw new IllegalArgumentException(
        	        "Ya existe un usuario con ese nombre.");
        }

        Usuario usuario = new Usuario();

        usuario.setUsername(request.getUsername());

        usuario.setPassword(
                passwordEncoder.encode(request.getPassword()));

        usuario.setRol(request.getRol());

        usuario.setActivo(true);

        usuario.setDebeCambiarPassword(true);

        usuario = usuarioRepository.save(usuario);

        return convertirRespuesta(usuario);

    }
    
    @Override
    public UsuarioResponse buscarPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado."));

        return convertirRespuesta(usuario);
    }
    
    @Override
    public UsuarioResponse actualizarUsuario(
            Long id,
            ActualizarUsuarioRequest request) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado."));

        if (!usuario.getUsername().equals(request.getUsername())
                && usuarioRepository.existsByUsername(request.getUsername())) {

        	throw new IllegalArgumentException(
        	        "El nombre de usuario ya existe.");
        }

        usuario.setUsername(request.getUsername());
        usuario.setRol(request.getRol());

        usuario = usuarioRepository.save(usuario);

        return convertirRespuesta(usuario);
    }
    
    @Override
    public void activarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado."));

        usuario.setActivo(true);

        usuarioRepository.save(usuario);

    }
    
    @Override
    public void desactivarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado."));

        Usuario usuarioAutenticado =
                obtenerUsuarioAutenticado();

        if (usuarioAutenticado.getId().equals(usuario.getId())) {

        	throw new IllegalArgumentException(
        	        "No puede desactivar el usuario con el que ha iniciado sesión.");

        }

        if (usuario.getRol() == Rol.ADMIN) {

            long administradoresActivos =

                    usuarioRepository.countByRolAndActivo(

                            Rol.ADMIN,

                            true);

            if (administradoresActivos <= 1) {

            	throw new IllegalArgumentException(
            	        "No puede desactivar el último administrador del sistema.");

            }

        }

        usuario.setActivo(false);

        usuarioRepository.save(usuario);

    }
    
    @Override
    public void cambiarPassword(Long id,
            ActualizarPasswordRequest request) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado."));

        usuario.setPassword(
                passwordEncoder.encode(request.getNuevaPassword()));

        usuario.setDebeCambiarPassword(false);

        usuarioRepository.save(usuario);

    }
    
   // metodos privados
    
    private Usuario obtenerUsuarioAutenticado() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return usuarioRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Usuario autenticado no encontrado."));

    }

}