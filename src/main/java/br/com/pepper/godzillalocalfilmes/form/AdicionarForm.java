package br.com.pepper.godzillalocalfilmes.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.pepper.godzillalocalfilmes.models.Filme;

public class AdicionarForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String diretor;
    @NotNull
    private int estoque;


    public AdicionarForm(String titulo, String diretor, int estoque) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.estoque = estoque;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    public  Filme adicionar(){
        Filme filmeAdicionado = new Filme();
        filmeAdicionado.setDiretor(diretor);
        filmeAdicionado.setEstoque(estoque);
        filmeAdicionado.setTitulo(titulo);
        return filmeAdicionado;


    }
    
}