package com.example.videostreamingapp.repository;

import com.example.videostreamingapp.model.Video;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface VideoRepository extends ReactiveCrudRepository<Video, Long> {
    // Método para buscar vídeos por categoria
    @Query("SELECT * FROM videos WHERE category_id = :categoryId")
    Flux<Video> findByCategoryId(Long categoryId);

    // Método para buscar vídeos favoritos
    Flux<Video> findByFavoriteTrue();

    Flux<Video> findByTitleContaining(String title);
    Flux<Video> findByPublicationDateBetween(LocalDateTime start, LocalDateTime end);
    // Método para buscar todos os vídeos marcados como favoritos
    @Query("SELECT v.* FROM videos v JOIN user_favorites uf ON v.id = uf.video_id WHERE uf.user_id = :userId")
    Flux<Video> findFavoritesByUserId(Long userId);

    Mono<Long> countByFavoriteTrue(); // Conta todos os vídeos favoritados

    @Query("SELECT AVG(views) FROM videos")
    Mono<Double> findAverageViews(); // Calcula a média de visualizações


    Flux<Video> findByPublicationDate(LocalDate publicationDate);

    Flux<Video> findByTitleContainingAndPublicationDate(String title, LocalDate publicationDate);
}


