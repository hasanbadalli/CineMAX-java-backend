package com.example.cinemax.mapper.actor;

import com.example.cinemax.dto.actor.ActorDto;
import com.example.cinemax.dto.actor.ActorWithMoviesDto;
import com.example.cinemax.dto.movie.MovieDto;
import com.example.cinemax.model.Actor;
import org.springframework.stereotype.Component;
import com.example.cinemax.model.Movie;
import java.util.List;

@Component
public class ActorMapperManual {

    public ActorDto toActorDto(Actor actor) {
        if (actor == null) return null;

        ActorDto dto = new ActorDto();
        dto.setId(actor.getId());
        dto.setFullname(actor.getFullname());
        dto.setMovieIds(actor.getMovies() != null
                ? actor.getMovies().stream().map(Movie::getId).toList()
                : null);
        return dto;
    }

    public ActorWithMoviesDto toActorWithMoviesDto(Actor actor) {
        if (actor == null) return null;

        ActorWithMoviesDto dto = new ActorWithMoviesDto();
        dto.setId(actor.getId());
        dto.setFullname(actor.getFullname());
        dto.setMovies(actor.getMovies() != null
                ? actor.getMovies().stream()
                .map(movie -> new MovieDto(movie.getId(), movie.getTitle(), movie.getGenre()))
                .toList()
                : null);
        return dto;
    }

    public List<ActorWithMoviesDto> toActorWithMoviesDtoList(List<Actor> actors) {
        return actors != null ? actors.stream().map(this::toActorWithMoviesDto).toList() : null;
    }
}
