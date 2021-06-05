package br.com.sitalomr.moviesapi.adapter.converter;

import br.com.sitalomr.moviesapi.adapter.rest.dto.response.PageResponse;
import br.com.sitalomr.moviesapi.domain.model.generic.GenericPage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class provides methods to convert objects of {@link GenericPage} type
 *
 * @param <S> Source type of {@link GenericPage}
 * @param <T> Target type of paging object
 */
@Component
public class GenericPageMapper<S, T> {

    /**
     * Allows to convert a {@link GenericPage} into a {@link PageResponse}, mapping the metadata and receiving the content
     *
     * @param genericPage Object of type {@link GenericPage} containing the pagination metadata
     * @param targetList  List of contents to put on the {@link PageResponse}
     * @return converted PageResponse
     */
    public PageResponse<T> toPageResponse(GenericPage<S> genericPage, List<T> targetList) {
        return PageResponse.<T>builder()
                .totalPages(genericPage.getTotalPages())
                .totalElements(genericPage.getTotalElements())
                .size(genericPage.getSize())
                .number(genericPage.getNumber())
                .numberOfElements(genericPage.getNumberOfElements())
                .first(genericPage.isFirst())
                .last(genericPage.isLast())
                .hasContent(genericPage.hasContent())
                .content(targetList)
                .build();
    }
}
