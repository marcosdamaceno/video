package com.example.videostreamingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Table("users") // Use o nome da tabela com a anotação Spring Data
@NoArgsConstructor
public class User {
        @Id
        private Long id;

        private String username;

        private String email;

        private String password;


}



