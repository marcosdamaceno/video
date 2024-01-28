package com.example.videostreamingapp.controller;

import com.example.videostreamingapp.model.UserFavorite;
import com.example.videostreamingapp.model.Video;
import com.example.videostreamingapp.service.FavoriteService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@NoArgsConstructor
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public Mono<ResponseEntity<Void>> addFavorite(@RequestBody UserFavorite favorite) {
        return favoriteService.addFavorite(favorite.getUserId(), favorite.getVideoId())
                .thenReturn(ResponseEntity.ok().build());
    }

    @GetMapping("/{userId}")
    public Flux<Video> getUserFavorites(@PathVariable Long userId) {
        return favoriteService.findFavoritesByUserId(userId);
    }

    @DeleteMapping("/{userId}/{videoId}")
    public Mono<ResponseEntity<Object>> deleteFavorite(@PathVariable Long userId, @PathVariable Long videoId) {
        return favoriteService.deleteFavorite(userId, videoId)
                .thenReturn(ResponseEntity.ok().build())
                .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
    }
}
