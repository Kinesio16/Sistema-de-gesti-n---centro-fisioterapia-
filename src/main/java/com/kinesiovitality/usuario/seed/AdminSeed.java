package com.kinesiovitality.usuario.seed;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kinesiovitality.common.enums.Rol;
import com.kinesiovitality.usuario.model.Usuario;
import com.kinesiovitality.usuario.repository.UsuarioRepository;

@Component
public class AdminSeed implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ADMIN_USERNAME}")
    private String adminUsername;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    public AdminSeed(UsuarioRepository usuarioRepository,
                     PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (!usuarioRepository.existsByUsername(adminUsername)) {
            Usuario admin = new Usuario();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRol(Rol.ADMIN);
            admin.setActivo(true);
            admin.setDebeCambiarPassword(true);

            usuarioRepository.save(admin);

            System.out.println("Usuario ADMIN semilla creado correctamente.");
        }
    }
}