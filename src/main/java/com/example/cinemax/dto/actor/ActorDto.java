package com.example.cinemax.dto.actor;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActorDto {
    private long id;
    private String fullname;
    private List<Long> movieIds;
}
