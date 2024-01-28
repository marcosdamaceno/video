/*package com.example.videostreamingapp;

import com.example.videostreamingapp.controller.FavoriteController;
import com.example.videostreamingapp.model.UserFavorite;
import com.example.videostreamingapp.model.Video;
import com.example.videostreamingapp.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;*/

//teste de integração
/*@WebFluxTest(FavoriteController.class)
class FavoriteControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FavoriteService favoriteService;

    @Test
    void addFavoriteTest() {
        Long userId = 1L;
        Long videoId = 2L;
        UserFavorite favorite = new UserFavorite(userId, videoId);


        Mockito.when(userFavoriteRepository.save(favorite)).thenReturn(Mono.just(favorite));

      //simula webtestclient
        webTestClient.post().uri("/favorites")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(favorite)
                .exchange()
                .expectStatus().isOk();

        // verifica se foi chamada
        Mockito.verify(userFavoriteRepository).save(favorite);
    }


    @Test
    void getUserFavoritesTest() {
        Long userId = 1L;
        Video video = new Video();

        // Simula repositorio
        Mockito.when(videoRepository.findFavoritesByUserId(userId)).thenReturn(Flux.just(video));


        webTestClient.get().uri("/favorites/" + userId)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Video.class).hasSize(1);


        Mockito.verify(videoRepository).findFavoritesByUserId(userId);
    }

}*/
