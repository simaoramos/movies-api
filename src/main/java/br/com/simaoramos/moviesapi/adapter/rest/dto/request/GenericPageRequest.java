package br.com.simaoramos.moviesapi.adapter.rest.dto.request;

import lombok.Data;

@Data
public class GenericPageRequest {
    private int page = 0;
    private int limit = 50;
}
