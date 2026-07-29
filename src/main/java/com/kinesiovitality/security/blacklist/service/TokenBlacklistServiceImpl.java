package com.kinesiovitality.security.blacklist.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.kinesiovitality.security.blacklist.model.TokenBlackList;
import com.kinesiovitality.security.blacklist.repository.TokenBlackListRepository;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    private final TokenBlackListRepository repository;

    public TokenBlacklistServiceImpl(TokenBlackListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void blacklistToken(
            String token,
            LocalDateTime fechaExpiracion,
            String motivo) {

        if (!repository.existsByTokenAndActivoTrue(token)) {

            TokenBlackList blacklist = new TokenBlackList();

            blacklist.setToken(token);
            blacklist.setFechaExpiracion(fechaExpiracion);
            blacklist.setFechaRevocacion(LocalDateTime.now());
            blacklist.setMotivo(motivo);
            blacklist.setActivo(true);

            repository.save(blacklist);
        }

    }

    @Override
    public boolean isBlacklisted(String token) {
        return repository.existsByTokenAndActivoTrue(token);
    }

}