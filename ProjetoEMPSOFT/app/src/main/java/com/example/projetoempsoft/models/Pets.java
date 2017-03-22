package com.example.projetoempsoft.models;

import java.util.List;

/**
 * Created by lucasfnf on 20/03/17.
 */
public class Pets {
    private Integer id;
    private String nome;
    private List<Vacina> vacinaList;

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

    public List<Vacina> getVacinaList() {
        return vacinaList;
    }

    public void setVacinaList(List<Vacina> vacinaList) {
        this.vacinaList = vacinaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pets pets = (Pets) o;

        if (!id.equals(pets.id)) return false;
        if (!nome.equals(pets.nome)) return false;
        return vacinaList.equals(pets.vacinaList);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + vacinaList.hashCode();
        return result;
    }
}
