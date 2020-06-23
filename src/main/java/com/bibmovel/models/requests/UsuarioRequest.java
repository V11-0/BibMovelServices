package com.bibmovel.models.requests;

import com.bibmovel.models.Usuario;

import java.util.List;

/**
 * Created by vinibrenobr11 on 16/06/2020 at 16:11
 */
public class UsuarioRequest {

    String operationKey;
    Usuario usuario;
    List<String> deviceInfo;

    public UsuarioRequest(String operationKey, Usuario usuario, List<String> deviceInfo) {
        this.operationKey = operationKey;
        this.usuario = usuario;
        this.deviceInfo = deviceInfo;
    }

    public UsuarioRequest() {
    }

    public String getOperationKey() {
        return operationKey;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<String> getDeviceInfo() {
        return deviceInfo;
    }

    public void setOperationKey(String operationKey) {
        this.operationKey = operationKey;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setDeviceInfo(List<String> deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
