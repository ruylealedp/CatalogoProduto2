package io.github.ruylealedp.quarkusManuntencaoCarro.domain.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "USUARIO")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private int idade;

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id;}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nome, user.nome) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email);
    }
}
