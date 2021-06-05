package br.com.sitalomr.moviesapi.adapter.jpa.repository;

import br.com.sitalomr.moviesapi.adapter.jpa.entity.GenreJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreJpaRepository extends JpaRepository<GenreJpaEntity, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
