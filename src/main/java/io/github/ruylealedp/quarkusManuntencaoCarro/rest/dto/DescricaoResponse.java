package io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto;

import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.Descricao;
import java.time.LocalDateTime;

public class DescricaoResponse {

    private String text;
    private LocalDateTime dataCriacao;

    public DescricaoResponse(String text, LocalDateTime dataCriacao) {
        this.text = text;
        this.dataCriacao = dataCriacao;
    }

    public static DescricaoResponse fromEntity(Descricao descricao) {
        return new DescricaoResponse(descricao.getText(), descricao.getDataCriacao());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
