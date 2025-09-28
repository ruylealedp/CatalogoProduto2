package io.github.ruylealedp.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {

    private static long nextId = 1; // Contador estático para IDs sequenciais

    private String id;
    @NotBlank(message = "O campo deve ser preenchido")
    private String nome;
    @NotBlank(message = "O campo deve ser preenchido")
    private String descricao;
    @NotNull(message = "O preço não pode ser nulo")
    @DecimalMin(value = "0.01", message = "Preencha o valor da mercadoria corretamente!")
    private BigDecimal preco;

    public Produto() {
        // Gera o ID sequencial automaticamente ao criar um novo produto
        this.id = String.valueOf(nextId++);
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                '}';
    }
}