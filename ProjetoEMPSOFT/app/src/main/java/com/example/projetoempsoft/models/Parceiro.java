package com.example.projetoempsoft.models;

/**
 * Created by lucasfnf on 27/03/17.
 */

abstract class Parceiro {
    private String rua;
    private String cidade;
    private String estado;
    private String complemento;
    private String numero;
    private String cep;
    private String telefone;

    Parceiro(String rua, String cidade, String estado, String complemento, String numero, String cep, String telefone) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.numero = numero;
        this.cep = cep;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parceiro parceiro = (Parceiro) o;

        if (rua != null ? !rua.equals(parceiro.rua) : parceiro.rua != null) return false;
        if (cidade != null ? !cidade.equals(parceiro.cidade) : parceiro.cidade != null)
            return false;
        if (estado != null ? !estado.equals(parceiro.estado) : parceiro.estado != null)
            return false;
        if (complemento != null ? !complemento.equals(parceiro.complemento) : parceiro.complemento != null)
            return false;
        if (numero != null ? !numero.equals(parceiro.numero) : parceiro.numero != null)
            return false;
        if (cep != null ? !cep.equals(parceiro.cep) : parceiro.cep != null) return false;
        return telefone != null ? telefone.equals(parceiro.telefone) : parceiro.telefone == null;

    }

    @Override
    public int hashCode() {
        int result = rua != null ? rua.hashCode() : 0;
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (complemento != null ? complemento.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        return result;
    }
}
