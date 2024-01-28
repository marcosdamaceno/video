package com.example.videostreamingapp.repository;

import com.example.videostreamingapp.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    // Método para verificar se um username já existe de forma reativa
    Mono<Boolean> existsByUsername(String username);

    // Método para verificar se um email já existe de forma reativa
    Mono<Boolean> existsByEmail(String email);

    // Método para buscar um usuário por username
    Mono<User> findByUsername(String username);

    // Método para buscar um usuário por email
    Mono<User> findByEmail(String email);

}
