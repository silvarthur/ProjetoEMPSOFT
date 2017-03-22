package com.example.projetoempsoft.models;

/**
 * Created by lucasfnf on 20/03/17.
 */
public class Endereco {
    private Integer id;
    private String rua;
    private String cidade;
    private String estado;
    private String complemento;
    private String numero;
    private String cep;

    public Endereco(Integer id, String rua, String cidade, String estado, String complemento, String numero, String cep) {
        this.id = id;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.numero = numero;
        this.cep = cep;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endereco endereco = (Endereco) o;

        if (id != null ? !id.equals(endereco.id) : endereco.id != null) return false;
        if (rua != null ? !rua.equals(endereco.rua) : endereco.rua != null) return false;
        if (cidade != null ? !cidade.equals(endereco.cidade) : endereco.cidade != null)
            return false;
        if (estado != null ? !estado.equals(endereco.estado) : endereco.estado != null)
            return false;
        if (complemento != null ? !complemento.equals(endereco.complemento) : endereco.complemento != null)
            return false;
        if (numero != null ? !numero.equals(endereco.numero) : endereco.numero != null)
            return false;
        return cep != null ? cep.equals(endereco.cep) : endereco.cep == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rua != null ? rua.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (complemento != null ? complemento.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        return result;
    }
}
