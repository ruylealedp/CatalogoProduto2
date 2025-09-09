package io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CreateReparoRequest {

    @NotBlank(message = "O campo 'mecanico' é obrigatório")
    private String mecanico;

    @NotBlank(message = "O campo 'servico' é obrigatório")
    private String servico;

    @NotNull(message = "O campo 'valorServico' é obrigatório")
    private BigDecimal valorServico;

    @NotNull(message = "O campo 'dataServico' é obrigatório")
    private LocalDate dataServico;

    private List<CreateDescricaoRequest> descricoes;

    // Getters e Setters
    public String getMecanico() { return mecanico; }
    public void setMecanico(String mecanico) { this.mecanico = mecanico; }
    public String getServico() { return servico; }
    public void setServico(String servico) { this.servico = servico; }
    public BigDecimal getValorServico() { return valorServico; }
    public void setValorServico(BigDecimal valorServico) { this.valorServico = valorServico; }
    public LocalDate getDataServico() { return dataServico; }
    public void setDataServico(LocalDate dataServico) { this.dataServico = dataServico; }
    public List<CreateDescricaoRequest> getDescricoes() { return descricoes; }
    public void setDescricoes(List<CreateDescricaoRequest> descricoes) { this.descricoes = descricoes; }
}