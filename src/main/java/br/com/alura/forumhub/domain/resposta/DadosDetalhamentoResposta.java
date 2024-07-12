package br.com.alura.forumhub.domain.resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(Long id,
                                        String mensagem,
                                        LocalDateTime dataCriacao) {

    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao());
    }
}
