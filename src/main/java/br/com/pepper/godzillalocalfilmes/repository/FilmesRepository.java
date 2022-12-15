package br.com.pepper.godzillalocalfilmes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pepper.godzillalocalfilmes.models.Filme;

@Repository
public interface FilmesRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByTituloContains(String titulo);

    List<Filme> findByTitulo(Filme titulo);

    boolean existsByTitulo(String titulo);


}