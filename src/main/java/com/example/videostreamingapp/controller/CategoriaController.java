package com.example.videostreamingapp.controller;

import com.example.videostreamingapp.model.Category;
import com.example.videostreamingapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriaController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Mono<ResponseEntity<Category>> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category)
                .map(savedCategory -> ResponseEntity.ok(savedCategory))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Category> getAllCategories() {
        return categoryService.findAllCategory(); // Deve retornar Flux<Category>
    }

}
