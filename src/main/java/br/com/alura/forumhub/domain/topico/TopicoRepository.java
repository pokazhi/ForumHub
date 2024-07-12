package com.iaco.forumhub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<com.iaco.forumhub.domain.topico.Topico, Long> {
    Page<com.iaco.forumhub.domain.topico.Topico> findAllByAtivoTrue(Pageable paginacao);
//    Page<Topico> findAll(Pageable paginacao);
}