package br.com.simaoramos.moviesapi.domain.port;

import br.com.simaoramos.moviesapi.domain.model.generic.GenericPage;
import br.com.simaoramos.moviesapi.domain.model.Genre;

import java.util.Optional;

public interface GenrePersistence<ID> {
    GenericPage<Genre<ID>> listByPageAndLimit(int page, int limit);
    Optional<Genre<ID>> findById(ID id);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, ID id);
    Genre<ID> save(Genre<ID> genre);
}
