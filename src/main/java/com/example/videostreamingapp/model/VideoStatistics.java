package com.example.videostreamingapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table("statistics")
public class VideoStatistics {
    private Long totalFavorites;
    private Double averageViews;

    public VideoStatistics(Long totalFavorites, Double averageViews) {
        this.totalFavorites = totalFavorites;
        this.averageViews = averageViews;
    }


}

