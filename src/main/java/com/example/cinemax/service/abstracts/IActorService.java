package com.example.cinemax.service.abstracts;

import com.example.cinemax.dto.actor.ActorDto;
import com.example.cinemax.dto.actor.ActorWithMoviesDto;
import com.example.cinemax.dto.actor.CreateActorDto;
import com.example.cinemax.model.Actor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IActorService {
    Page<ActorDto> getAllActors(int page, int size);
    Page<ActorWithMoviesDto> getAllActorsWithMovies(int page, int size);
    ActorDto addActor(CreateActorDto createActorDto);
}
