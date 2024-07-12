package br.com.alura.forumhub.domain.topico;

import br.com.alura.forumhub.domain.ValidacaoException;
import br.com.alura.forumhub.domain.curso.Curso;
import br.com.alura.forumhub.domain.curso.CursoRepositorio;
import br.com.alura.forumhub.domain.resposta.DadosNovaResposta;
import br.com.alura.forumhub.domain.usuario.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private br.com.alura.forumhub.domain.topico.TopicoRepository topicoRepository;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Autowired
    private AuthenticationService authenticationService;

    public Curso getCurso(br.com.alura.forumhub.domain.topico.DadosNovoTopico dadosNovoTopico) {
        var curso = cursoRepositorio.getCurso(dadosNovoTopico.curso());
        if (curso == null) {
            throw new ValidacaoException("Curso não encontrado. Cadastre curso antes!");
        }
        return curso;
    }

    public br.com.alura.forumhub.domain.topico.DadosDetalhamentoTopico getDadosDetalhamentoTopicoAtualizado(Long id, br.com.alura.forumhub.domain.topico.DadosAtualizarTopico dadosAtualizarTopico, Authentication authentication) {
        var usuario = authenticationService.getUsuario(authentication);
        Optional<br.com.alura.forumhub.domain.topico.Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado");
        }
        if (!Objects.equals(topico.get().getAutor().getEmail(), usuario.getEmail())) {
            throw new ValidacaoException("Sem permissão para atualizar o tópico!");
        }
        topico.get().updateTopic(dadosAtualizarTopico);
        topicoRepository.save(topico.get());
        var dadosTopicoAtualizado = new br.com.alura.forumhub.domain.topico.DadosDetalhamentoTopico(topico.get());
        return dadosTopicoAtualizado;
    }

    public br.com.alura.forumhub.domain.topico.DadosDetalhamentoTopico getDadosDetalhamentoTopicoDeletado(Long id, Authentication authentication) {
        var usuario = authenticationService.getUsuario(authentication);
        Optional<br.com.alura.forumhub.domain.topico.Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado");
        }
        if (!Objects.equals(topico.get().getAutor().getEmail(), usuario.getEmail())) {
            throw new ValidacaoException("Sem permissão para atualizar o tópico!");
        }
        topico.get().deleteTopic();
        topicoRepository.save(topico.get());
        var dadosTopicoDeletado = new br.com.alura.forumhub.domain.topico.DadosDetalhamentoTopico(topico.get());
        return dadosTopicoDeletado;
    }

    public Optional<br.com.alura.forumhub.domain.topico.Topico> getTopico(DadosNovaResposta dadosNovaResposta) {
        var topico = topicoRepository.findById(dadosNovaResposta.topico());
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado.");
        }
        return topico;
    }
}

