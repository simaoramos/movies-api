package br.com.simaoramos.moviesapi.adapter.service;

import br.com.simaoramos.moviesapi.adapter.jpa.entity.GenreJpaEntity;
import br.com.simaoramos.moviesapi.adapter.jpa.repository.GenreJpaRepository;
import br.com.simaoramos.moviesapi.domain.model.Genre;
import br.com.simaoramos.moviesapi.domain.model.generic.GenericPage;
import br.com.simaoramos.moviesapi.domain.port.GenrePersistence;
import br.com.simaoramos.moviesapi.adapter.converter.mapstruct.GenreMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenrePersistenceService implements GenrePersistence<Long> {
    private final GenreJpaRepository genreJpaRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenericPage<Genre<Long>> listByPageAndLimit(int page, int limit) {
        Page<GenreJpaEntity> entityPage = this.genreJpaRepository.findAll(PageRequest.of(page, limit));

        List<Genre<Long>> genreList = this.genreMapper.toGenreList(entityPage.toList());

        return GenericPage.<Genre<Long>>builder()
                .totalPages(entityPage.getTotalPages())
                .totalElements(entityPage.getTotalElements())
                .size(entityPage.getSize())
                .number(entityPage.getNumber())
                .numberOfElements(entityPage.getNumberOfElements())
                .first(entityPage.isFirst())
                .last(entityPage.isLast())
                .hasContent(entityPage.hasContent())
                .content(genreList)
                .build();
    }

    @Override
    public Optional<Genre<Long>> findById(Long id) {
        Optional<GenreJpaEntity> genreJpaEntityOpt = this.genreJpaRepository.findById(id);
        return genreJpaEntityOpt.map(this.genreMapper::toGenre);
    }

    @Override
    public boolean existsByName(String name) {
        return this.genreJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return this.genreJpaRepository.existsByNameAndIdNot(name, id);
    }

    @Override
    public Genre<Long> save(Genre<Long> newGenre) {
        GenreJpaEntity genreJpaEntity = this.genreMapper.toGenreJpaEntity(newGenre);
        genreJpaEntity = this.genreJpaRepository.save(genreJpaEntity);
        return this.genreMapper.toGenre(genreJpaEntity);
    }
}
