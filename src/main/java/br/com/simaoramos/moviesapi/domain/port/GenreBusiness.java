package br.com.simaoramos.moviesapi.domain.port;

import br.com.simaoramos.moviesapi.domain.model.generic.GenericPage;
import br.com.simaoramos.moviesapi.domain.exception.GenreExistingNameException;
import br.com.simaoramos.moviesapi.domain.exception.GenreNotExistsException;
import br.com.simaoramos.moviesapi.domain.model.Genre;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public abstract class GenreBusiness<ID> {
    private final GenrePersistence<ID> genrePersistence;

    /**
     * Allows to retrieve a list of {@link Genre} records.
     *
     * @param page  The page required to list the records.
     * @param limit The limit of records in the page.
     * @return List of {@link Genre} retrieved by page and limit.
     */
    public GenericPage<Genre<ID>> list(int page, int limit) {
        return this.genrePersistence.listByPageAndLimit(page, limit);
    }

    /**
     * Allows to retrieve an existing {@link Genre} record in the persistence layer.
     *
     * @param id Identification of the record to be found.
     * @return {@link Genre} object found.
     * @throws GenreNotExistsException Thrown when no {@link Genre} records were found to the given id.
     */
    public Genre<ID> find(ID id) throws GenreNotExistsException {
        return this.genrePersistence.findById(id)
                .orElseThrow(GenreNotExistsException::new);
    }

    /**
     * Allows to create a new {@link Genre} record in the persistence layer.
     *
     * @param genre {@link Genre} object containing the data to be persisted.
     * @return The {@link Genre} persisted object.
     * @throws GenreExistingNameException Thrown when already exists a genre record with the same given name.
     */
    public Genre<ID> create(Genre<ID> genre) throws GenreExistingNameException {
        if (this.genrePersistence.existsByName(genre.getName())) {
            throw new GenreExistingNameException();
        }

        genre.setCreatedAt(LocalDateTime.now());

        return this.genrePersistence.save(genre);
    }

    /**
     * Allows to update an existing {@link Genre} record in the persistence layer.
     *
     * @param id    Identification of the record to be updated.
     * @param genre {@link Genre} object containing the data to be persisted.
     * @return The {@link Genre} persisted object.
     * @throws GenreNotExistsException    Thrown when does not exists a genre record with the given id.
     * @throws GenreExistingNameException Thrown when already exists another genre record with the same given name.
     */
    public Genre<ID> update(ID id, Genre<ID> genre) throws GenreNotExistsException, GenreExistingNameException {
        Genre<ID> existingGenre = this.find(id);

        if (this.genrePersistence.existsByNameAndIdNot(genre.getName(), id)) {
            throw new GenreExistingNameException();
        }

        existingGenre.setName(genre.getName());
        existingGenre.setUpdatedAt(LocalDateTime.now());

        return this.genrePersistence.save(existingGenre);
    }
}
