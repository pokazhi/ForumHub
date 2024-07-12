package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.resposta.DadosDetalhamentoResposta;
import br.com.alura.forumhub.domain.resposta.DadosNovaResposta;
import br.com.alura.forumhub.domain.resposta.Resposta;
import br.com.alura.forumhub.domain.resposta.RespostaRepository;
import br.com.alura.forumhub.domain.topico.TopicoService;
import br.com.alura.forumhub.domain.usuario.AuthenticationService;
import br.com.alura.forumhub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/respostas")
public class RespostaController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public ResponseEntity responderTopico(@RequestBody @Valid DadosNovaResposta dadosNovaResposta,
                                          UriComponentsBuilder builder, Authentication authentication) {
        var usuario = authenticationService.getUsuario(authentication);
        var topico = topicoService.getTopico(dadosNovaResposta);

        var resposta = new Resposta(null, dadosNovaResposta.mensagem(), topico.get(), LocalDateTime.now(), usuario, false);

        respostaRepository.save(resposta);
        var uri = builder.path("/topicos/{id}").buildAndExpand(resposta.getId()).toUri();
        var dto = new DadosDetalhamentoResposta(resposta);
        return ResponseEntity.created(uri).body(dto);
    }


}
