package io.github.ruylealedp.quarkusManuntencaoCarro.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Reparo")
public class Reparo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mecanico;

    @Column(nullable = false)
    private String servico;

    @Column(name = "valor_servico", nullable = false)
    private BigDecimal valorServico;

    @Column(name = "data_servico", nullable = false)
    private LocalDate dataServico;

    @OneToMany(mappedBy = "reparo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Descricao> descricoes;

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

    public List<Descricao> getDescricoes() {
        return descricoes;
    }

    public void setDescricoes(List<Descricao> descricoes) {
        this.descricoes = descricoes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reparo reparo = (Reparo) o;
        return Objects.equals(id, reparo.id) && Objects.equals(mecanico, reparo.mecanico) && Objects.equals(servico, reparo.servico) && Objects.equals(valorServico, reparo.valorServico) && Objects.equals(dataServico, reparo.dataServico) && Objects.equals(descricoes, reparo.descricoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mecanico, servico, valorServico, dataServico, descricoes);
    }
}
