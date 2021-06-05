package br.com.sitalomr.moviesapi.domain.model.generic;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericPage<T> {
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private int numberOfElements;
    private boolean first;
    private boolean last;
    private boolean hasContent;
    private List<T> content;

    public boolean hasContent() {
        return this.hasContent;
    }
}
