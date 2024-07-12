package br.com.alura.forumhub.domain.usuario;

public record UsuarioDetalhes (
        Long id,
        String nome,
        String senha,
        String email
) {

    public UsuarioDetalhes(br.com.alura.forumhub.domain.usuario.Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getEmail());
    }
}