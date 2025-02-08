package com.example.cinemax.service.concrets;

import com.example.cinemax.dto.actor.ActorDto;
import com.example.cinemax.dto.actor.ActorWithMoviesDto;
import com.example.cinemax.dto.actor.CreateActorDto;
import com.example.cinemax.mapper.actor.ActorMapperManual;
import com.example.cinemax.model.Actor;
import com.example.cinemax.model.Movie;
import com.example.cinemax.repository.ActorRepository;
import com.example.cinemax.repository.MovieRepository;
import com.example.cinemax.service.abstracts.IActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService implements IActorService {
    private final ActorRepository actorRepository;
    private final ActorMapperManual actorMapperManual;
    private final MovieRepository movieRepository;

    @Override
    public Page<ActorDto> getAllActors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fullname").descending());

        Page<Actor> actors = actorRepository.findAllWithPagging(pageable);

        // Manuel olarak sayfalı DTO dönüşümü
        List<ActorDto> dtos = actors.getContent().stream()
                .map(actorMapperManual::toActorDto)
                .toList();

        return new PageImpl<>(dtos, pageable, actors.getTotalElements());
    }

    @Override
    public Page<ActorWithMoviesDto> getAllActorsWithMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fullname").descending());
        Page<Actor> actors = actorRepository.findAllWithMovies(pageable);

        // Manuel olarak sayfalı DTO dönüşümü
        List<ActorWithMoviesDto> dtos = actors.getContent().stream()
                .map(actorMapperManual::toActorWithMoviesDto)
                .toList();

        return new PageImpl<>(dtos, pageable, actors.getTotalElements());
    }

    @Override
    public ActorDto addActor(CreateActorDto createActorDto){
        List<Long> movieIds = createActorDto.getMovieIds();
        List<Movie> existingMovies = movieRepository.findAllById(movieIds);

        if(existingMovies.size() != movieIds.size()){
            throw new RuntimeException("Some movies not found.");
        }
        Actor actor = new Actor();
        actor.setFullname(createActorDto.getFullname());
        actor.setMovies(existingMovies);
        actorRepository.save(actor);
        ActorDto actorDto = actorMapperManual.toActorDto(actor);
        return actorDto;
    }
}
