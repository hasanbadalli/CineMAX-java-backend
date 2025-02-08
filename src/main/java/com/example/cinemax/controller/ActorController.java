package com.example.cinemax.controller;

import com.example.cinemax.dto.ApiResponse;
import com.example.cinemax.dto.ErrorResponse;
import com.example.cinemax.dto.actor.ActorDto;
import com.example.cinemax.dto.actor.ActorWithMoviesDto;
import com.example.cinemax.dto.actor.CreateActorDto;
import com.example.cinemax.dto.actor.UpdateActorDto;
import com.example.cinemax.model.Actor;
import com.example.cinemax.service.concrets.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ActorDto>>> getAllActors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ActorDto> actorDtos = actorService.getAllActors(page, size);
        return ResponseEntity.ok(new ApiResponse<>("Successful", HttpStatus.OK.value(), actorDtos));
    }

    @GetMapping("/withMovies")
    public ResponseEntity<ApiResponse<Page<ActorWithMoviesDto>>> getAllActorsWithMovies(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size){
        Page<ActorWithMoviesDto> actorDtos = actorService.getAllActorsWithMovies(page, size);
        return ResponseEntity.ok(new ApiResponse<>("Successful", HttpStatus.OK.value(), actorDtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ActorDto>> createActor(@RequestBody @Valid CreateActorDto actorDto) {
        try{
            ActorDto savedActor = actorService.addActor(actorDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Actor created successfully", HttpStatus.CREATED.value(), savedActor));
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("Failed to create actor", HttpStatus.BAD_REQUEST.value(), null));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ActorDto>> updateActor(@RequestBody @Valid UpdateActorDto actorDto) {
        try {
            ActorDto updatedActor = actorService.updateActor(actorDto);
            return ResponseEntity.ok(new ApiResponse<>("Actor updated successfully", HttpStatus.OK.value(), updatedActor));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.NOT_FOUND.value(), null));
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("Failed to update actor", HttpStatus.BAD_REQUEST.value(), null));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteActor(@PathVariable Long id) {
        try {
            actorService.deleteActor(id);
            return ResponseEntity.ok(new ApiResponse<>("Actor deleted successfully", HttpStatus.OK.value(), null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Actor not found", HttpStatus.NOT_FOUND.value(), null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>("Failed to delete actor", HttpStatus.BAD_REQUEST.value(), null));
        }
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> (error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value")
                ));


        Map<String, Object> response = Map.of(
                "status", 400,
                "message", "Validation error",
                "errors", errors
        );


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hello World");
    }

}
