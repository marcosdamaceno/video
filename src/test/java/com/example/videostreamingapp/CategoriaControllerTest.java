package com.example.videostreamingapp;

import com.example.videostreamingapp.controller.CategoriaController;
import com.example.videostreamingapp.model.Category;
import com.example.videostreamingapp.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CategoryService categoryService;

      @Test
    void createCategoryEndpointTest() {
        Category newCategory = new Category("Action");

        Mockito.when(categoryService.createCategory(newCategory)).thenReturn(Mono.just(newCategory));

        webTestClient.post()
                .uri("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newCategory)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Category.class)
                .isEqualTo(newCategory);
    }
    @Test
    void getAllCategoriesEndpointTest() {
        Category category1 = new Category("Action");
        Category category2 = new Category("Comedy");

        Mockito.when(categoryService.findAllCategory()).thenReturn(Flux.just(category1, category2));

        webTestClient.get()
                .uri("/categories")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Category.class)
                .hasSize(2)
                .contains(category1, category2);
    }



}
