package com.example.projetoempsoft.models;

/**
 * Created by lucasfnf on 20/03/17.
 */
public class PetShop extends Parceiro {
    private Integer id;
    private String nome;

    public PetShop(Integer id, String nome, String rua, String cidade, String estado, String complemento, String numero, String cep, String telefone) {
        super(rua, cidade, estado, complemento, numero, cep, telefone);
        this.id = id;
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PetShop petShop = (PetShop) o;

        if (id != null ? !id.equals(petShop.id) : petShop.id != null) return false;
        return nome != null ? nome.equals(petShop.nome) : petShop.nome == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }
}
