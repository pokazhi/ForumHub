package br.com.alura.forumhub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<br.com.alura.forumhub.domain.topico.Topico, Long> {
    Page<br.com.alura.forumhub.domain.topico.Topico> findAllByAtivoTrue(Pageable paginacao);
//    Page<Topico> findAll(Pageable paginacao);
}
