package br.com.simaoramos.moviesapi.adapter.service;

import br.com.simaoramos.moviesapi.domain.port.GenreBusiness;
import org.springframework.stereotype.Service;

@Service
public class GenreBusinessService extends GenreBusiness<Long> {
    public GenreBusinessService(GenrePersistenceService genrePersistence) {
        super(genrePersistence);
    }
}
