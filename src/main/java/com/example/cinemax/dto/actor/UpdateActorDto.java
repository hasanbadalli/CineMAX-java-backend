package com.example.cinemax.dto.actor;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UpdateActorDto {
    @NotNull(message = "Id can not be null")
    private long id;


    @Size(min = 3, max = 100, message = "Full name must be beetweem 3 and 100")
    private String fullname;

    private List<Long> movieIds;
}
