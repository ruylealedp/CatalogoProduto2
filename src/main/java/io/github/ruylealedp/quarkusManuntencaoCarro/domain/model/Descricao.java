package io.github.ruylealedp.quarkusManuntencaoCarro.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Descricao")
public class Descricao extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "reparo_id")
    @JsonBackReference
    private Reparo reparo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Reparo getReparo() {
        return reparo;
    }

    public void setReparo(Reparo reparo) {
        this.reparo = reparo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Descricao descricao = (Descricao) o;
        return Objects.equals(id, descricao.id) && Objects.equals(text, descricao.text) && Objects.equals(dataCriacao, descricao.dataCriacao) && Objects.equals(reparo, descricao.reparo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, dataCriacao, reparo);
    }
}
