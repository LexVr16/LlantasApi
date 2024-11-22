package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.repository.ProductoRepository;
import com.maycollins.LlantasApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
}
