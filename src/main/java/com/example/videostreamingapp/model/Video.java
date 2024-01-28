package com.example.videostreamingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table("videos")
public class Video {
    @Id
    private Long id;
    private String title;
    private String description;
    private String url;
    private LocalDateTime publicationDate;
    private boolean favorite;
    @Column("category_id")
    private Long categoryId; // Assume que a tabela de categoria tem um campo 'id'


    // Método para definir a categoria do vídeo
    public void setCategory(Category category) {
        // Atualiza o categoryId com base na categoria fornecida
        this.categoryId = category.getId();

        }

    public Video(Long id, String title, String description, String url, LocalDateTime publicationDate, boolean favorite, Long categoryId) {
        this.id = id;
        this.title = title;
        this.description =description;
        this.url=url;
        this.publicationDate =publicationDate;
        this.favorite =favorite;
        this.categoryId =categoryId;

        ;

    }
}
