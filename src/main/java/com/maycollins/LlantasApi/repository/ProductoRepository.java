package com.maycollins.LlantasApi.repository;

import com.maycollins.LlantasApi.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
