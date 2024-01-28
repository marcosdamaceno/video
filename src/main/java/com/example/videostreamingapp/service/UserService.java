package com.example.videostreamingapp.service;

import com.example.videostreamingapp.exception.UserAlreadyExistsException;
import com.example.videostreamingapp.model.User;
import com.example.videostreamingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Método para recuperar todos os usuários
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> registerUser(User user) {
        // Verifique a existência de nome de usuário e e-mail de maneira reativa
        return Mono.zip(
                        userRepository.existsByUsername(user.getUsername()),
                        userRepository.existsByEmail(user.getEmail()),
                        (existsByUsername, existsByEmail) -> {
                            if (existsByUsername) {
                                throw new UserAlreadyExistsException("O usuário já existe: " + user.getUsername());
                            }
                            if (existsByEmail) {
                                throw new UserAlreadyExistsException("O email já existe: " + user.getEmail());
                            }
                            return user;
                        })
                .flatMap(u -> userRepository.save(u)); // Salve o usuário depois de passar pelas verificações
    }

    // Métodos adicionais como encontrar por ID, atualizar e excluir usuários podem ser adicionados aqui
}
