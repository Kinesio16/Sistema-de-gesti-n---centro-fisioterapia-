package com.kinesiovitality.usuario.service;

import java.util.List; 

import com.kinesiovitality.usuario.dto.ActualizarPasswordRequest;
import com.kinesiovitality.usuario.dto.UsuarioRequest;
import com.kinesiovitality.usuario.dto.UsuarioResponse;
import com.kinesiovitality.usuario.dto.ActualizarUsuarioRequest;

public interface UsuarioService {

    UsuarioResponse crearUsuario(UsuarioRequest request);

    List<UsuarioResponse> listarUsuarios();

    UsuarioResponse buscarPorId(Long id);

    UsuarioResponse actualizarUsuario(
            Long id,
            ActualizarUsuarioRequest request);

    void activarUsuario(Long id);

    void desactivarUsuario(Long id);

    void cambiarPassword(Long id, ActualizarPasswordRequest request);

}