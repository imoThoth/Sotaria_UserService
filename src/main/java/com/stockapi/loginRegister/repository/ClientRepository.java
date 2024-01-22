package com.stockapi.loginRegister.repository;

import com.stockapi.loginRegister.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByUsername(String clientName);
}
