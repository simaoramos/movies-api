package br.com.sitalomr.moviesapi.adapter.converter.mapstruct;

import br.com.sitalomr.moviesapi.adapter.jpa.entity.GenreJpaEntity;
import br.com.sitalomr.moviesapi.adapter.rest.dto.persist.GenrePersist;
import br.com.sitalomr.moviesapi.adapter.rest.dto.response.GenreResponse;
import br.com.sitalomr.moviesapi.adapter.rest.dto.update.GenreUpdate;
import br.com.sitalomr.moviesapi.domain.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    // jpa entity mappins
    List<Genre<Long>> toGenreList(List<GenreJpaEntity> genreJpaEntityList);
    GenreJpaEntity toGenreJpaEntity(Genre<Long> genre);
    Genre<Long> toGenre(GenreJpaEntity genreJpaEntity);

    // rest dto mappings
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Genre<Long> toGenre(GenrePersist genrePersist);
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Genre<Long> toGenre(GenreUpdate genreUpdate);
    List<GenreResponse> toGenreResponseList(List<Genre<Long>> genreList);
    GenreResponse toGenreResponse(Genre<Long> genre);
}
