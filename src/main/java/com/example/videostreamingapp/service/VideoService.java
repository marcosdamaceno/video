package com.example.videostreamingapp.service;

import com.example.videostreamingapp.model.Video;
import com.example.videostreamingapp.repository.CategoryRepository;
import com.example.videostreamingapp.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository, CategoryRepository categoryRepository) {
        this.videoRepository = videoRepository;
        this.categoryRepository = categoryRepository;
    }

    public Flux<Video> findAllVideos() {
        return videoRepository.findAll();
    }

    public Mono<Video> findVideoById(Long id) {
        return videoRepository.findById(id);
    }

    public Mono<Video> saveVideo(Video video) {
        // Assumindo que o vídeo já tenha uma categoria definida por ID
        return videoRepository.save(video);
    }

    public Flux<Video> findAllByCategory(Long categoryId) {
        // Primeiro, encontre a categoria pelo ID, depois encontre todos os vídeos associados
        return categoryRepository.findById(categoryId)
                .flatMapMany(cat -> videoRepository.findByCategoryId(cat.getId()));
    }

    public Mono<Video> updateVideo(Long id, Video videoDetails) {
        return videoRepository.findById(id)
                .flatMap(video -> {
                    video.setTitle(videoDetails.getTitle());
                    video.setDescription(videoDetails.getDescription());
                    video.setUrl(videoDetails.getUrl());
                    video.setPublicationDate(videoDetails.getPublicationDate());
                    // Se videoDetails contiver o status de favorito e a categoria, defina-os também
                    video.setFavorite(videoDetails.isFavorite());
                    //video.setCategoryId(videoDetails.getCategoryId());
                    return videoRepository.save(video);
                });
    }

    public Flux<Video> associateVideosToCategory(Long categoryId, List<Long> videoIds) {
        return Flux.fromIterable(videoIds)
                .flatMap(videoId -> videoRepository.findById(videoId))
                .doOnNext(video -> video.setCategoryId(categoryId))
                .flatMap(videoRepository::save);
    }

    public Mono<Void> deleteVideo(Long id) {
        return videoRepository.findById(id)
                .flatMap(video -> videoRepository.delete(video))
                .then(); // Retorna um Mono<Void> após a conclusão da operação de delete
    }

    public Flux<Video> findAllFavorites() {
        // Usa o método findByFavoriteTrue do repositório para buscar vídeos favoritos
        return videoRepository.findByFavoriteTrue();
    }
    // Busca vídeos favoritos para um usuário específico
    public Flux<Video> findFavoritesByUserId(Long userId) {
        return videoRepository.findFavoritesByUserId(userId);
    }
    public Mono<Long> getTotalFavoriteVideos() {
        return videoRepository.countByFavoriteTrue();
    }

    public Mono<Double> getAverageViews() {
        return videoRepository.findAverageViews();
    }

    public Flux<Video> findVideos(String title, LocalDate publicationDate) {
        if (title != null && publicationDate != null) {
            return videoRepository.findByTitleContainingAndPublicationDate(title, publicationDate);
        } else if (title != null) {
            return videoRepository.findByTitleContaining(title);
        } else if (publicationDate != null) {
            return videoRepository.findByPublicationDate(publicationDate);
        } else {
            return videoRepository.findAll();
        }
    }
}
