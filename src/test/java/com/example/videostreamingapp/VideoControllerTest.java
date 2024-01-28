package com.example.videostreamingapp;

import com.example.videostreamingapp.controller.VideoController;
import com.example.videostreamingapp.model.Video;
import com.example.videostreamingapp.service.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@WebFluxTest(VideoController.class)
class VideoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private VideoService videoService;

    @Test
    void getAllVideosTest() {
        Mockito.when(videoService.findAllVideos()).thenReturn(Flux.just(new Video(1L, "Titulo", "Descricao", "URL", LocalDateTime.now(), false, 1L)));

        webTestClient.get().uri("/api/videos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Video.class).hasSize(1);
    }

    @Test
    void toggleFavoriteTest() {
        Video video = new Video(1L, "Titulo", "Descricao", "URL", LocalDateTime.now(), false, 1L);
        Mockito.when(videoService.findVideoById(1L)).thenReturn(Mono.just(video));
        Mockito.when(videoService.saveVideo(video)).thenReturn(Mono.just(video));

        webTestClient.patch().uri("/api/videos/1/favorite")
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void getVideoStatisticsTest() {
        // Suponha que existam 5 vídeos favoritados e a média de visualizações é 150.
        Mockito.when(videoService.getTotalFavoriteVideos()).thenReturn(Mono.just(5L));
        Mockito.when(videoService.getAverageViews()).thenReturn(Mono.just(150.0));

        webTestClient.get().uri("/api/videos/statistics")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.totalFavorites").isEqualTo(5)
                .jsonPath("$.averageViews").isEqualTo(150.0);
    }

}
