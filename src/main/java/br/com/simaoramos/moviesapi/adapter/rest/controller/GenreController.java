package br.com.simaoramos.moviesapi.adapter.rest.controller;

import br.com.simaoramos.moviesapi.adapter.converter.GenericPageMapper;
import br.com.simaoramos.moviesapi.adapter.converter.mapstruct.GenreMapper;
import br.com.simaoramos.moviesapi.adapter.rest.dto.persist.GenrePersist;
import br.com.simaoramos.moviesapi.adapter.rest.dto.request.GenericPageRequest;
import br.com.simaoramos.moviesapi.adapter.rest.dto.response.GenreResponse;
import br.com.simaoramos.moviesapi.adapter.rest.dto.response.PageResponse;
import br.com.simaoramos.moviesapi.adapter.rest.dto.update.GenreUpdate;
import br.com.simaoramos.moviesapi.domain.model.Genre;
import br.com.simaoramos.moviesapi.domain.model.generic.GenericPage;
import br.com.simaoramos.moviesapi.domain.port.GenreBusiness;
import br.com.simaoramos.moviesapi.domain.exception.GenreExistingNameException;
import br.com.simaoramos.moviesapi.domain.exception.GenreNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "genres", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class GenreController {
    private final GenreBusiness<Long> genreBusiness;
    private final GenericPageMapper<Genre<Long>, GenreResponse> genericPageMapper;
    private final GenreMapper genreMapper;

    @GetMapping
    public ResponseEntity<PageResponse<GenreResponse>> list(GenericPageRequest pageRequest) {
        GenericPage<Genre<Long>> genres = this.genreBusiness.list(pageRequest.getPage(), pageRequest.getLimit());
        List<GenreResponse> genreResponseList = this.genreMapper.toGenreResponseList(genres.getContent());
        PageResponse<GenreResponse> pageResponse = this.genericPageMapper.toPageResponse(genres, genreResponseList);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreResponse> findById(@PathVariable("id") Long id) throws GenreNotExistsException {
        Genre<Long> genre = this.genreBusiness.find(id);
        return ResponseEntity.ok(this.genreMapper.toGenreResponse(genre));
    }

    @PostMapping
    public ResponseEntity<GenreResponse> create(@RequestBody GenrePersist persist) throws GenreExistingNameException {
        Genre<Long> genre = this.genreMapper.toGenre(persist);
        genre = this.genreBusiness.create(genre);
        return ResponseEntity.ok(this.genreMapper.toGenreResponse(genre));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenreResponse> update(
            @PathVariable("id") Long id,
            @RequestBody GenreUpdate update
    ) throws GenreNotExistsException, GenreExistingNameException {
        Genre<Long> genre = this.genreMapper.toGenre(update);
        genre = this.genreBusiness.update(id, genre);
        return ResponseEntity.ok(this.genreMapper.toGenreResponse(genre));
    }
}
