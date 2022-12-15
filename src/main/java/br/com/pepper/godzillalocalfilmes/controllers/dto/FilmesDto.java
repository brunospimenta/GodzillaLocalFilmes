package br.com.pepper.godzillalocalfilmes.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.pepper.godzillalocalfilmes.models.Filme;

public class FilmesDto {

    private Long id;
    private String titulo;    
    private String diretor;    
    private int estoque;
    

    public FilmesDto(Filme filme) {
        this.id = filme.getId();
        this.titulo = filme.getTitulo();
        this.diretor = filme.getDiretor();
        this.estoque = filme.getEstoque();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public static List<FilmesDto> converter(List<Filme> filmes) {
        return filmes.stream().map(FilmesDto::new).collect(Collectors.toList());
    }



}