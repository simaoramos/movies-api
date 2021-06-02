package br.com.sitalomr.moviesapi.genre.service;

import br.com.sitalomr.moviesapi.genre.entity.Genre;
import br.com.sitalomr.moviesapi.genre.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Page<Genre> findAllPageable() {
        return this.genreRepository.findAll(PageRequest.of(0, 50));
    }
}
