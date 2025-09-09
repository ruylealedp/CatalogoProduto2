package io.github.ruylealedp.quarkusManuntencaoCarro.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="ABASTECIMENTO")
public class Abastecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePosto;
    private BigDecimal litros;
    private BigDecimal valorGasto;
    private BigDecimal kmPercorrido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Abastecimento that = (Abastecimento) o;
        return Objects.equals(id, that.id) && Objects.equals(nomePosto, that.nomePosto) && Objects.equals(litros, that.litros) && Objects.equals(valorGasto, that.valorGasto) && Objects.equals(kmPercorrido, that.kmPercorrido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomePosto, litros, valorGasto, kmPercorrido);
    }
}
