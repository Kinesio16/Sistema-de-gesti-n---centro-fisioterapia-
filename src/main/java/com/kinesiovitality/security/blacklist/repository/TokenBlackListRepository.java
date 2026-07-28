package com.kinesiovitality.security.blacklist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kinesiovitality.security.blacklist.model.TokenBlackList;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

    Optional<TokenBlackList> findByTokenAndActivoTrue(String token);

    boolean existsByTokenAndActivoTrue(String token);
}