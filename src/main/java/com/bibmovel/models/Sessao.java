package com.bibmovel.models;

import java.sql.Timestamp;

/**
 * Created by vinibrenobr11 on 20/06/2020 at 15:32
 */
public class Sessao {

    private final int id;
    private final int id_usuario;
    private final String hash_code;
    private final Timestamp data_inicio;

    public Sessao(int id, int id_usuario, String hash_code, Timestamp data_inicio) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.hash_code = hash_code;
        this.data_inicio = data_inicio;
    }

    public int getId() {
        return id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getHash_code() {
        return hash_code;
    }

    public Timestamp getData_inicio() {
        return data_inicio;
    }
}
