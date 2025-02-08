//package com.example.cinemax.mapper;
//
//import com.example.cinemax.dto.actor.ActorDto;
//import com.example.cinemax.dto.actor.ActorWithMoviesDto;
//import com.example.cinemax.dto.movie.MovieDto;
//import com.example.cinemax.model.Actor;
//import com.example.cinemax.model.Movie;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//import org.springframework.data.domain.Page;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface ActorMapper {
//    @Mapping(target = "movieIds", source = "movies", qualifiedByName = "mapMoviesToIds")
//    ActorDto actorToActorDto(Actor actor);
//
//    @Mapping(target = "movies" ,  source = "movies")
//    ActorWithMoviesDto actorToActorWithMoviesDto(Actor actor);
//
//    @Named("mapMoviesToIds")
//    static List<Long> mapMoviesToIds(List<Movie> movies) {
//        return movies != null ? movies.stream().map(Movie::getId).toList() : null;
//    }
//
//    @Named("mapMoviesToMovieDtos")
//    static List<MovieDto> mapMoviesToMovieDtos(List<Movie> movies) {
//        return movies != null ? movies.stream()
//                .map(movie -> new MovieDto(movie.getId(), movie.getTitle(), movie.getGenre()))
//                .toList()
//                : null;
//    }
//
//
//    List<ActorDto> actorsListToActorsDtoList(List<Actor> actors);
//    List<ActorWithMoviesDto> actorsListToActorWithMoviesDtoList(List<Actor> actors);
//
//    default Page<ActorDto> actorsPageToActorsDtoPage(Page<Actor> actors) {
//        return actors.map(this::actorToActorDto);
//    }
//
//    default Page<ActorWithMoviesDto> actorsPageToActorsWithMoviesDtoPage(Page<Actor> actors) {
//        return actors.map(this::actorToActorWithMoviesDto);
//    }
//}
