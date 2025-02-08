package com.example.cinemax.dto.actor;

import com.example.cinemax.dto.movie.MovieDto;
import lombok.Data;

import java.util.List;

@Data
public class ActorWithMoviesDto {
    private long id;
    private String fullname;
    private List<MovieDto> movies;
}
