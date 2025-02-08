package com.example.cinemax.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieDto {
    private long id;
    private String title;
    private String genre;
}