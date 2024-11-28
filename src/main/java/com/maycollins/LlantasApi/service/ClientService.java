package com.maycollins.LlantasApi.service;


import com.maycollins.LlantasApi.model.Client;
import com.maycollins.LlantasApi.model.ClientCategory;
import com.maycollins.LlantasApi.model.ClientStatus;
import com.maycollins.LlantasApi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Obtener todos los clientes
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Obtener cliente por ID
    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    // Crear nuevo cliente
    public Client createClient(Client client) {
        client.setRegistrationDate(new Date());
        if (client.getStatus() == null) {
            client.setStatus(ClientStatus.ACTIVE);
        }
        return clientRepository.save(client);
    }

    // Actualizar cliente
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    // Eliminar cliente
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    // Buscar por número de documento
    public Optional<Client> findByDocumentNumber(String documentNumber) {
        return clientRepository.findByDocumentNumber(documentNumber);
    }

    // Actualizar categoría del cliente
    public Client updateClientCategory(Integer id, ClientCategory category) {
        return clientRepository.findById(id).map(client -> {
            client.setCategory(category);
            return clientRepository.save(client);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }
    // Bloquear cliente
    public Client blockClient(Integer id) {
        return clientRepository.findById(id).map(client -> {
            client.setStatus(ClientStatus.BLOCKED);
            return clientRepository.save(client);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    // Actualizar historial
    public Client updateHistory(Integer id, String historyNote) {
        return clientRepository.findById(id).map(client -> {
            String currentHistory = client.getHistoryNotes();
            String newHistory = (currentHistory != null ? currentHistory + "\n" : "")
                    + new Date() + ": " + historyNote;
            client.setHistoryNotes(newHistory);
            return clientRepository.save(client);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
