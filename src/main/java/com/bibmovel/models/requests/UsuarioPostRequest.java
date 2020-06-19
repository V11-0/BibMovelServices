package com.bibmovel.models.requests;

import com.bibmovel.models.Usuario;

/**
 * Created by vinibrenobr11 on 16/06/2020 at 16:11
 */
public class UsuarioPostRequest {

    String operationKey;
    Usuario usuario;

    public UsuarioPostRequest(String operationKey, Usuario usuario) {
        this.operationKey = operationKey;
        this.usuario = usuario;
    }

    public UsuarioPostRequest() {
    }

    public String getOperationKey() {
        return operationKey;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setOperationKey(String operationKey) {
        this.operationKey = operationKey;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
