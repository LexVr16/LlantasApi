package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.UserAccount;
import com.maycollins.LlantasApi.model.UserStatus;
import com.maycollins.LlantasApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Obtener todos los usuarios
    public List<UserAccount> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener usuario por ID
    public Optional<UserAccount> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Crear nuevo usuario
    public UserAccount createUser(UserAccount user) {
        user.setCreationDate(new Date());
        user.setLastAccess(new Date());
        return userRepository.save(user);
    }

    // Actualizar usuario
    public UserAccount updateUser(UserAccount user) {
        return userRepository.save(user);
    }

    // Eliminar usuario
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    // Iniciar sesión
    public Optional<UserAccount> login(String email, String password) {
        Optional<UserAccount> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            UserAccount loggedUser = user.get();
            loggedUser.setLastAccess(new Date());
            return Optional.of(userRepository.save(loggedUser));
        }
        return Optional.empty();
    }

    // Bloquear usuario
    public UserAccount blockUser(Integer id) {
        return userRepository.findById(id).map(user -> {
            user.setStatus(UserStatus.BLOCKED);
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Actualizar perfil
    public UserAccount updateProfile(Integer id, UserAccount updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setLastName(updatedUser.getLastName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setAddress(updatedUser.getAddress());
            user.setProfileImage(updatedUser.getProfileImage());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
