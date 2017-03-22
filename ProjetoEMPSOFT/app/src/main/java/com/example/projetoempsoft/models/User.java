package com.example.projetoempsoft.models;

import java.util.List;

/**
 * Created by lucasfnf on 20/03/17.
 */
public class User {
    private Integer id;
    private String nome;
    private List<Pets> petsList;
    private Endereco endereco;

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

    public List<Pets> getPetsList() {
        return petsList;
    }

    public void setPetsList(List<Pets> petsList) {
        this.petsList = petsList;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!nome.equals(user.nome)) return false;
        return petsList.equals(user.petsList);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + petsList.hashCode();
        return result;
    }
}
