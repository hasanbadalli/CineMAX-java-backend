package com.example.cinemax.dto.actor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateActorDto {

    @NotNull(message = "Full name can not be null")
    @Size(min = 3, max = 100, message = "Full name must be beetweem 3 and 100")
    private String fullname;

    private List<Long> movieIds;
}
