package br.com.pepper.godzillalocalfilmes.form;

import br.com.pepper.godzillalocalfilmes.models.Filme;
import br.com.pepper.godzillalocalfilmes.repository.FilmesRepository;

public class AlugarForm {

    public Filme alugar(Long id, FilmesRepository filmesRepository) {

        Filme filme = filmesRepository.getById(id);
        return filme;
    }
}