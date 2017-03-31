package com.example.projetoempsoft.models;

import java.text.SimpleDateFormat;
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

    public String getStringTipoAgendamento(){
        if(this.tipoAgendamento.equals(TipoAgendamento.BANHO)){
            return "Banho";
        }else if(this.tipoAgendamento.equals(TipoAgendamento.BANHO_E_TOSA)){
            return "Banho e Tosa";
        }else if(this.tipoAgendamento.equals(TipoAgendamento.TOSA)){
            return "Tosa";
        }else if(this.tipoAgendamento.equals(TipoAgendamento.CONSULTA)){
            return "Consulta";
        }else if(this.tipoAgendamento.equals(TipoAgendamento.BANHO_TOSA_CONSULTA)){
            return "Banho, Tosa e Consulta";
        }return null;
    }

    public String getStringStatus(){
        if(this.getStatus().equals(StatusAgendamento.AGENDADO)){
            return "Agendado";
        }else if(this.getStatus().equals(StatusAgendamento.CANCELADO)){
            return "Cancelado";
        }else if(this.getStatus().equals(StatusAgendamento.EM_ANDAMENTO)){
            return "Em Andamento";
        }else if(this.getStatus().equals(StatusAgendamento.PENDENTE)){
            return "Pendente";
        }else if(this.getStatus().equals(StatusAgendamento.CONCLUIDO)){
            return "Concluído";
        }return null;
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

    public String getFormatedData(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM");
        return df.format(this.data).toString();
    }

    public String getHora() {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm aa");
        return df.format(this.data).toString();
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
