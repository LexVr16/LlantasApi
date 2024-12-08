package com.maycollins.LlantasApi.controller;


import com.maycollins.LlantasApi.model.Client;
import com.maycollins.LlantasApi.model.ClientCategory;
import com.maycollins.LlantasApi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Obtener todos los clientes
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo cliente
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.getClientById(id)
                .map(existingClient -> {
                    client.setClientId(id);
                    return ResponseEntity.ok(clientService.updateClient(client));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        return clientService.getClientById(id)
                .map(client -> {
                    clientService.deleteClient(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar por número de documento
    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<Client> findByDocumentNumber(@PathVariable String documentNumber) {
        return clientService.findByDocumentNumber(documentNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar categoría del cliente
    @PutMapping("/{id}/category")
    public ResponseEntity<Client> updateClientCategory(
            @PathVariable Integer id,
            @RequestParam ClientCategory category) {
        try {
            Client updatedClient = clientService.updateClientCategory(id, category);
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Bloquear cliente
    @PutMapping("/{id}/block")
    public ResponseEntity<Client> blockClient(@PathVariable Integer id) {
        try {
            Client blockedClient = clientService.blockClient(id);
            return ResponseEntity.ok(blockedClient);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar historial
    @PutMapping("/{id}/history")
    public ResponseEntity<Client> updateHistory(
            @PathVariable Integer id,
            @RequestParam String historyNote) {
        try {
            Client updatedClient = clientService.updateHistory(id, historyNote);
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}