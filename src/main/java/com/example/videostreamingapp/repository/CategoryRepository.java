package com.example.videostreamingapp.repository;

import com.example.videostreamingapp.model.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {
    Mono<Category> findByName(String name);

    Mono<Category> findById(Long id);

    Mono<Boolean> existsByName(String name);


}


