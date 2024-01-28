package com.example.videostreamingapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Setter
@Table("user_favorites")
public class UserFavorite {
    @Id
    private Long userId;
    private Long videoId;


    public UserFavorite(Long userId, Long videoId) {
    }


}