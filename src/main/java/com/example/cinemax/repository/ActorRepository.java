package com.example.cinemax.repository;


import com.example.cinemax.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    @EntityGraph(attributePaths = "movies")
    @Query("SELECT a FROM Actor a")
    Page<Actor> findAllWithMovies(Pageable pageable);

    @Query("SELECT a FROM Actor a")
    Page<Actor> findAllWithPagging(Pageable pageable);
}
