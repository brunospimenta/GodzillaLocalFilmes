package br.com.pepper.godzillalocalfilmes.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pepper.godzillalocalfilmes.controllers.dto.FilmesDto;
import br.com.pepper.godzillalocalfilmes.form.AdicionarForm;
import br.com.pepper.godzillalocalfilmes.form.AlugarForm;
import br.com.pepper.godzillalocalfilmes.models.Filme;
import br.com.pepper.godzillalocalfilmes.repository.FilmesRepository;

@RequestMapping("/localdora")
@RestController
public class FilmesController {

    @Autowired
    private FilmesRepository filmesRepository;

    // exercicio 2 do teste 3 a5Solutions
    @GetMapping("/godzilla")
    public List<FilmesDto> listaComFiltro(@RequestParam("titulo") String titulo) {
        List<Filme> filme = filmesRepository.findByTituloContains(titulo);
        return FilmesDto.converter(filme);
    }

    @GetMapping("/")
    public List<FilmesDto> listarTodos() {
        List<Filme> filmes = filmesRepository.findAll();
        return FilmesDto.converter(filmes);
    }

    @PutMapping("/godzilla/{id}")
    @Transactional
    public ResponseEntity<?> alugar(@PathVariable Long id, AlugarForm form) {
        Optional<Filme> optional = filmesRepository.findById(id);

        if (optional.get().getEstoque() == 0) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Filme sem estoque!");

        } else {

            Filme filme = form.alugar(id, filmesRepository);
            optional.get().setEstoque(optional.get().getEstoque() - 1);

            return ResponseEntity.ok(new FilmesDto(filme));
        }

    }

    @PostMapping("/adicionar")
    @Transactional
    public ResponseEntity<?> adicionar(@RequestBody @Valid AdicionarForm form){
        Filme filme = form.adicionar();
        return ResponseEntity.status(HttpStatus.CREATED).body(filmesRepository.save(filme)); 
    }

}
