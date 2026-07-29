package com.kinesiovitality.security.blacklist.service;

import java.time.LocalDateTime;

public interface TokenBlacklistService {

    void blacklistToken(
            String token,
            LocalDateTime fechaExpiracion,
            String motivo);

    boolean isBlacklisted(String token);

}