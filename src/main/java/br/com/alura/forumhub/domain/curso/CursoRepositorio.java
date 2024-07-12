package br.com.alura.forumhub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepositorio extends JpaRepository<br.com.alura.forumhub.domain.curso.Curso, Long> {

    @Query("select c from Curso c where lower(c.nome) = lower(:curso)")
    br.com.alura.forumhub.domain.curso.Curso getCurso(String curso);
}
