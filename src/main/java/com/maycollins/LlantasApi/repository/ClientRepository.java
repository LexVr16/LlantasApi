package com.maycollins.LlantasApi.repository;


import com.maycollins.LlantasApi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByDocumentNumber(String documentNumber);
    boolean existsByDocumentNumber(String documentNumber);
}

