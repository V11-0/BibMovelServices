package com.bibmovel.models;

/**
 * Created by vinibrenobr11 on 20/06/2020 at 15:32
 */
public class Sessao {

    private int id;
    private int idUsuario;
    private String hashCode;
    private String dataInicio;
    private String deviceUUID;

    public Sessao(int id, int idUsuario, String hashCode, String dataInicio) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.hashCode = hashCode;
        this.dataInicio = dataInicio;
    }

    public Sessao() {
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getHashCode() {
        return hashCode;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID;
    }
}
