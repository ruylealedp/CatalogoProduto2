package io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAbastecimentoRequest {

    @NotBlank(message="Preenchimento obrigatorio")
    private String nomePosto;
    @NotNull(message="Preenchimento obrigatorio")
    private double litros;
    @NotNull(message="Preenchimento obrigatorio")
    private double valorGasto;
    @NotNull(message="Preenchimento obrigatorio")
    private double kmPercorrido;

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }

    public double getKmPercorrido() {
        return kmPercorrido;
    }

    public void setKmPercorrido(double kmPercorrido) {
        this.kmPercorrido = kmPercorrido;
    }

}
