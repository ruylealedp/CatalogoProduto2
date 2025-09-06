package io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateAbastecimentoRequest {

    @NotBlank(message="Preenchimento obrigatorio")
    private String nomePosto;

    // Garante que o valor não seja nulo.
    @NotNull(message = "Litros é obrigatório.")
    @DecimalMin(value = "1.0", inclusive = false, message = "O valor deve ser maior que zero.")
    private BigDecimal litros;

    // Garante que o valor não seja nulo.
    @NotNull(message = "Valor gasto é obrigatório.")
    @DecimalMin(value = "1.0", inclusive = false, message = "O valor gasto deve ser maior que zero.")
    private BigDecimal valorGasto;

    // Garante que o valor não seja nulo.
    @NotNull(message = "Km percorrido é obrigatório.")
    @DecimalMin(value = "1.0", message = "A distância percorrida precisa ser preenchida.")
    private BigDecimal kmPercorrido;

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public BigDecimal getLitros() {
        return litros;
    }

    public void setLitros(BigDecimal litros) {
        this.litros = litros;
    }

    public BigDecimal getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(BigDecimal valorGasto) {
        this.valorGasto = valorGasto;
    }

    public BigDecimal getKmPercorrido() {
        return kmPercorrido;
    }

    public void setKmPercorrido(BigDecimal kmPercorrido) {
        this.kmPercorrido = kmPercorrido;
    }
}
