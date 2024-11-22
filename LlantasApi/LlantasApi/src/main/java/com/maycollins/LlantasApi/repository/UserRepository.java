package com.maycollins.LlantasApi.repository;

import com.maycollins.LlantasApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}