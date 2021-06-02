package br.com.sitalomr.moviesapi.movie.repository;

import br.com.sitalomr.moviesapi.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
