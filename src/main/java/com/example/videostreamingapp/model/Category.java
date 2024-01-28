package com.example.videostreamingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Table("categories")
@NoArgsConstructor
public class Category {

    @Id
    private Long id;
    private String name;

    // Removido a lista de vídeos e métodos relacionados

    public Category(String name) {
        this.name = name;
    }

}
