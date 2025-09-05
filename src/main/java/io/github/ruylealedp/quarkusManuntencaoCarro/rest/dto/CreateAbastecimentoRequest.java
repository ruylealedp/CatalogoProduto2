package io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto;

import jakarta.persistence.Column;

public class CreateAbastecimentoRequest {

    private String nomePosto;
    private double litros;
    private double valorGasto;
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
