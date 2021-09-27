package br.com.simaoramos.moviesapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = {"id"})
public class Genre<ID> {
    private ID id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
