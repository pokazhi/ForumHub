package br.com.alura.forumhub.domain.usuario;

import br.com.alura.forumhub.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private br.com.alura.forumhub.domain.usuario.UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public br.com.alura.forumhub.domain.usuario.Usuario criarNovoUsuario(br.com.alura.forumhub.domain.usuario.UsuarioDetalhes dadosAutenticacao) {
        if (usuarioRepository.existsByEmail(dadosAutenticacao.email())) {
            throw new ValidacaoException("Usuário com o mesmo e-mail encontrado");
        }

        br.com.alura.forumhub.domain.usuario.Usuario usuario = new br.com.alura.forumhub.domain.usuario.Usuario();
        usuario.setNome(dadosAutenticacao.nome());
        usuario.setEmail(dadosAutenticacao.email());
        usuario.setSenha(passwordEncoder.encode(dadosAutenticacao.senha()));

        return usuarioRepository.save(usuario);
    }
}
