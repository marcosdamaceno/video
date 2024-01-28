package com.example.videostreamingapp.service;

import com.example.videostreamingapp.model.Category;
import com.example.videostreamingapp.model.Video;
import com.example.videostreamingapp.repository.CategoryRepository;
import com.example.videostreamingapp.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private VideoRepository videoRepository;

    public Mono<Category> createCategory(Category category) {
        return categoryRepository.existsByName(category.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("Uma categoria com o mesmo nome já existe."));
                    }

                    // Salva apenas a categoria, sem tentar associar vídeos diretamente
                    return categoryRepository.save(category);
                });
    }

    public Flux<Category> findAllCategory() {
        return categoryRepository.findAll(); // Retorna um Flux<Category>
    }

}