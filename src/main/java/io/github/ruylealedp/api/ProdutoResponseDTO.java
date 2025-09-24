package io.github.ruylealedp.api;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private String nome;
    private BigDecimal preco;
    private String descricao;
    private String id; // Adicionado para incluir o ID na resposta

    public ProdutoResponseDTO(String nome, BigDecimal preco, String descricao, String id) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getId() {
        return id;
    }
}