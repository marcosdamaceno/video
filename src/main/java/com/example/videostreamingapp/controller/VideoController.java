package com.example.videostreamingapp.controller;

import com.example.videostreamingapp.model.Video;
import com.example.videostreamingapp.model.VideoStatistics;
import com.example.videostreamingapp.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public Flux<Video> getAllVideos() {
        return videoService.findAllVideos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Video>> getVideoById(@PathVariable Long id) {
        return videoService.findVideoById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/videos")
    public Flux<Video> getVideos(@RequestParam(required = false) String title,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publicationDate) {
        return videoService.findVideos(title, publicationDate);
    }

    @PatchMapping("/{videoId}/favorite")
    public Mono<ResponseEntity<Object>> toggleFavorite(@PathVariable Long videoId) {
        return videoService.findVideoById(videoId)
                .flatMap(video -> {
                    video.setFavorite(!video.isFavorite());
                    return videoService.saveVideo(video).thenReturn(ResponseEntity.ok().build());
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public Flux<Video> listVideosByCategory(@PathVariable Long categoryId) {
        return videoService.findAllByCategory(categoryId);
    }

    @GetMapping("/favorites")
    public Flux<Video> listFavoriteVideos() {
        return videoService.findAllFavorites();
    }

    @PostMapping
    public Mono<Video> createVideo(@RequestBody Video video) {
        return videoService.saveVideo(video);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteVideo(@PathVariable Long id) {
        return videoService.deleteVideo(id)
                .thenReturn(ResponseEntity.ok().build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/statistics")
    public Mono<ResponseEntity<VideoStatistics>> getVideoStatistics() {
        Mono<Long> totalFavorites = videoService.getTotalFavoriteVideos();
        Mono<Double> averageViews = videoService.getAverageViews();

        return Mono.zip(totalFavorites, averageViews, VideoStatistics::new)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
