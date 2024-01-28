package com.example.videostreamingapp;

import com.example.videostreamingapp.model.UserFavorite;
import com.example.videostreamingapp.repository.UserFavoriteRepository;
import com.example.videostreamingapp.repository.VideoRepository;
import com.example.videostreamingapp.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
//teste unitário
class FavoriteServiceTest {

    private final VideoRepository videoRepository = Mockito.mock(VideoRepository.class);
    private final UserFavoriteRepository userFavoriteRepository = Mockito.mock(UserFavoriteRepository.class);
    private final FavoriteService favoriteService = new FavoriteService(videoRepository, userFavoriteRepository);

    @Test
    void addFavoriteTest() {
        Long userId = 1L;
        Long videoId = 1L;
        UserFavorite favorite = new UserFavorite(userId, videoId);

        Mockito.when(userFavoriteRepository.save(Mockito.any(UserFavorite.class))).thenReturn(Mono.just(favorite));

        StepVerifier.create(favoriteService.addFavorite(userId, videoId))
                .verifyComplete();

        Mockito.verify(userFavoriteRepository).save(Mockito.any(UserFavorite.class));
    }
}
