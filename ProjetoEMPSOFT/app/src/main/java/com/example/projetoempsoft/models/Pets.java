package com.example.projetoempsoft.models;

import java.util.List;

/**
 * Created by lucasfnf on 20/03/17.
 */
public class Pets {
    private Integer id;
    private String nome;
    private User dono;

    public Pets(Integer id, String nome, User dono) {
        this.id = id;
        this.nome = nome;
        this.dono = dono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public User getDono() {
        return dono;
    }

    public void setDono(User dono) {
        this.dono = dono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pets pets = (Pets) o;

        if (id != null ? !id.equals(pets.id) : pets.id != null) return false;
        if (nome != null ? !nome.equals(pets.nome) : pets.nome != null) return false;
        return dono != null ? dono.equals(pets.dono) : pets.dono == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (dono != null ? dono.hashCode() : 0);
        return result;
    }
}
