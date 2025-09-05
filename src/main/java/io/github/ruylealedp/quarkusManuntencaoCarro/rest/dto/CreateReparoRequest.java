package io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateReparoRequest {

    @NotBlank(message="Preenchimento obrigatorio")
    private String mecanico;
    @NotBlank(message="Preenchimento obrigatorio")
    private String servico;
    @NotNull(message="Preenchimento obrigatorio")
    private BigDecimal valorServico;
    @NotNull(message="Preenchimento obrigatorio")
    private LocalDate dataServico;

    public String getMecanico() {
        return mecanico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public LocalDate getDataServico() {
        return dataServico;
    }

    public void setDataServico(LocalDate dataServico) {
        this.dataServico = dataServico;
    }
}
