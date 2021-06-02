package br.com.sitalomr.moviesapi.movie.service;

import br.com.sitalomr.moviesapi.movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
}
