package br.com.sitalomr.moviesapi.genre.repository;

import br.com.sitalomr.moviesapi.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
