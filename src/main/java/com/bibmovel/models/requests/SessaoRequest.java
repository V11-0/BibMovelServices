package com.bibmovel.models.requests;

import com.bibmovel.models.Sessao;

/**
 * Created by vinibrenobr11 on 24/06/2020 at 16:35
 */
public class SessaoRequest {

    private String operationKey;
    private Sessao sessao;

    public SessaoRequest(String operationKey, Sessao sessao, String deviceUUID) {
        this.operationKey = operationKey;
        this.sessao = sessao;
    }

    public SessaoRequest() {}

    public String getOperationKey() {
        return operationKey;
    }

    public void setOperationKey(String operationKey) {
        this.operationKey = operationKey;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}
