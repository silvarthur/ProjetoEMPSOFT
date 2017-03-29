package com.example.projetoempsoft.models;

/**
 * Created by nathan on 3/26/17.
 */
public class Item {
    private Integer id;
    private String titulo;
    private Double preco;
    private String descricao;

    public Item(Integer id, String titulo, Double preco, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
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

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (titulo != null ? !titulo.equals(item.titulo) : item.titulo != null) return false;
        if (preco != null ? !preco.equals(item.preco) : item.preco != null) return false;
        return descricao != null ? descricao.equals(item.descricao) : item.descricao == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (preco != null ? preco.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }
}
