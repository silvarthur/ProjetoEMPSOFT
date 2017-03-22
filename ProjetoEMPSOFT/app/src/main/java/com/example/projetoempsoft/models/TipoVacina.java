package com.example.projetoempsoft.models;

/**
 * Created by lucasfnf on 20/03/17.
 */
public class TipoVacina {
    private Integer id;
    private String nome;
    private String descricao;

    public TipoVacina(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoVacina tipoVacina = (TipoVacina) o;

        if (!nome.equals(tipoVacina.nome)) return false;
        return descricao.equals(tipoVacina.descricao);
    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + descricao.hashCode();
        return result;
    }
}
