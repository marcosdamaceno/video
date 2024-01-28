package com.example.videostreamingapp;

import com.example.videostreamingapp.model.Category;
import com.example.videostreamingapp.repository.CategoryRepository;
import com.example.videostreamingapp.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void createCategoryTest() {
        Category newCategory = new Category("Action");

        Mockito.when(categoryRepository.existsByName(newCategory.getName())).thenReturn(Mono.just(false));
        Mockito.when(categoryRepository.save(newCategory)).thenReturn(Mono.just(newCategory));

        Mono<Category> result = categoryService.createCategory(newCategory);

        StepVerifier.create(result)
                .expectNextMatches(category -> category.getName().equals("Action"))
                .verifyComplete();
    }

}
