package io.github.ruylealedp.quarkusManuntencaoCarro.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Reparo")
public class Reparo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mecanico", nullable = false)
    private String mecanico;
    @Column(name = "servico", nullable = false)
    private String servico;
    @Column(name = "valorServico", nullable = false)
    private BigDecimal valorServico;
    @Column(name = "dataServico", nullable = false)
    private LocalDate dataServico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reparo reparo = (Reparo) o;
        return Objects.equals(id, reparo.id) && Objects.equals(mecanico, reparo.mecanico) && Objects.equals(servico, reparo.servico) && Objects.equals(valorServico, reparo.valorServico) && Objects.equals(dataServico, reparo.dataServico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mecanico, servico, valorServico, dataServico);
    }
}