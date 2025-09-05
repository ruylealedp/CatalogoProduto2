package io.github.ruylealedp.quarkusManuntencaoCarro.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="ABASTECIMENTO")
public class Abastecimento extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nomePosto", nullable = false)
    private String nomePosto;
    @Column(name="litros", nullable = false)
    private double litros;
    @Column(name = "valorGasto", nullable = false)
    private double valorGasto;
    @Column(name = "kmPercorrido", nullable = false)
    private double kmPercorrido;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Abastecimento that = (Abastecimento) o;
        return Double.compare(litros, that.litros) == 0 && Double.compare(valorGasto, that.valorGasto) == 0 && Double.compare(kmPercorrido, that.kmPercorrido) == 0 && Objects.equals(id, that.id) && Objects.equals(nomePosto, that.nomePosto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomePosto, litros, valorGasto, kmPercorrido);
    }
}
