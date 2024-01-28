package com.example.videostreamingapp.repository;

import com.example.videostreamingapp.model.UserFavorite;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserFavoriteRepository extends ReactiveCrudRepository<UserFavorite, Long> {

    // Método para encontrar todos os vídeos favoritos de um usuário específico
    Flux<UserFavorite> findByUserId(Long userId);

    // Método para verificar se um vídeo específico já é favorito de um usuário

    Mono<Boolean> existsByUserIdAndVideoId(Long userId, Long videoId);

    // Método para deletar um vídeo específico dos favoritos de um usuário
    Mono<Void> deleteByUserIdAndVideoId(Long userId, Long videoId);


}
