package com.example.projetoempsoft.models;

import java.util.Date;

/**
 * Created by lucasfnf on 20/03/17.
 */
public class Vacina {
    private Integer id;
    private Pets pet;
    private String tipoVacina;
    private Date data;
    private Date dataRetorno;
    private Veterinario veterinario;

    public Vacina(Integer id, Pets pet, String tipoVacina, Date data, Date dataRetorno, Veterinario veterinario) {
        this.id = id;
        this.pet = pet;
        this.tipoVacina = tipoVacina;
        this.data = data;
        this.dataRetorno = dataRetorno;
        this.veterinario = veterinario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pets getPet() {
        return pet;
    }

    public void setPet(Pets pet) {
        this.pet = pet;
    }

    public String getTipoVacina() {
        return tipoVacina;
    }

    public void setTipoVacina(String tipoVacina) {
        this.tipoVacina = tipoVacina;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacina vacina = (Vacina) o;

        if (id != null ? !id.equals(vacina.id) : vacina.id != null) return false;
        if (pet != null ? !pet.equals(vacina.pet) : vacina.pet != null) return false;
        if (tipoVacina != null ? !tipoVacina.equals(vacina.tipoVacina) : vacina.tipoVacina != null)
            return false;
        if (data != null ? !data.equals(vacina.data) : vacina.data != null) return false;
        if (dataRetorno != null ? !dataRetorno.equals(vacina.dataRetorno) : vacina.dataRetorno != null)
            return false;
        return veterinario != null ? veterinario.equals(vacina.veterinario) : vacina.veterinario == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pet != null ? pet.hashCode() : 0);
        result = 31 * result + (tipoVacina != null ? tipoVacina.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (dataRetorno != null ? dataRetorno.hashCode() : 0);
        result = 31 * result + (veterinario != null ? veterinario.hashCode() : 0);
        return result;
    }
}
