package me.dm7.barcodescanner.zxing.sample.model;

/**
 * Created by CarlosEmilio on 26/09/2016.
 */

public class ListItemPontos {
    private String nomeEscola;
    private String dataSemana;
    private String dataMes;
    private String confrimado;
    private String horario;





    public ListItemPontos(String nomeEscola, String dataSemana, String dataMes, String confrimado, String horario) {
        this.nomeEscola = nomeEscola;
        this.dataSemana = dataSemana;
        this.dataMes = dataMes;
        this.confrimado = confrimado;
        this.horario = horario;

    }

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }

    public String getDataSemana() {
        return dataSemana;
    }

    public void setDataSemana(String dataSemana) {
        this.dataSemana = dataSemana;
    }

    public String getDataMes() {
        return dataMes;
    }

    public void setDataMes(String dataMes) {
        this.dataMes = dataMes;
    }

    public String getConfrimado() {
        return confrimado;
    }

    public void setConfrimado(String confrimado) {
        this.confrimado = confrimado;
    }


    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
