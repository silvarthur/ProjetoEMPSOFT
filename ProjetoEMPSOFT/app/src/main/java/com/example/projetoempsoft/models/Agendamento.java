package com.example.projetoempsoft.models;

import com.example.projetoempsoft.adapters.ScheduleAdapter;

import java.util.Date;

/**
 * Created by lucasfnf on 20/03/17.
 */
public class Agendamento {
    private Integer id;
    private User user;
    private TipoAgendamento tipoAgendamento;
    private Date data;
    private StatusAgendamento status;

    public Agendamento(Integer id, User user, TipoAgendamento tipoAgendamento, Date data, StatusAgendamento status) {
        this.id = id;
        this.user = user;
        this.tipoAgendamento = tipoAgendamento;
        this.data = data;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TipoAgendamento getTipoAgendamento() {
        return tipoAgendamento;
    }

    public void setTipoAgendamento(TipoAgendamento tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agendamento that = (Agendamento) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (tipoAgendamento != that.tipoAgendamento) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return status == that.status;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (tipoAgendamento != null ? tipoAgendamento.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
