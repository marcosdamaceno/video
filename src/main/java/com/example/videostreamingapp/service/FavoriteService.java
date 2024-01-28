package com.example.videostreamingapp.service;

import com.example.videostreamingapp.model.UserFavorite;
import com.example.videostreamingapp.model.Video;
import com.example.videostreamingapp.repository.UserFavoriteRepository;
import com.example.videostreamingapp.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
@Service
public class FavoriteService {

    private final VideoRepository videoRepository;
    private final UserFavoriteRepository userFavoriteRepository;

    @Autowired
    public FavoriteService(VideoRepository videoRepository, UserFavoriteRepository userFavoriteRepository) {
        this.videoRepository = videoRepository;
        this.userFavoriteRepository = userFavoriteRepository;
    }

    public Mono<UserFavorite> addFavorite(Long userId, Long videoId) {
        return userFavoriteRepository.existsByUserIdAndVideoId(userId, videoId)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalStateException("O vídeo já está marcado como favorito"));
                    } else {
                        UserFavorite favorite = new UserFavorite(userId, videoId);
                        return userFavoriteRepository.save(favorite);
                    }
                })
                .switchIfEmpty(Mono.defer(() -> {
                    // Caso o Mono<Boolean> seja nulo, por segurança podemos tratar como se não existisse.
                    UserFavorite favorite = new UserFavorite(userId, videoId);
                    return userFavoriteRepository.save(favorite);
                }));
    }


    public Flux<Video> findFavoritesByUserId(Long userId) {
        return videoRepository.findFavoritesByUserId(userId);
    }

    public Mono<Void> deleteFavorite(Long userId, Long videoId) {
        return userFavoriteRepository.deleteByUserIdAndVideoId(userId, videoId);
    }

}
