package com.iaco.forumhub.domain.curso;

public record CursoDetalhes(Long id,
                            String nome,
                            com.iaco.forumhub.domain.curso.Categoria categoria) {

    public CursoDetalhes(com.iaco.forumhub.domain.curso.Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
