package com.bibmovel.controller;

import com.bibmovel.models.Sessao;
import com.bibmovel.models.Usuario;
import com.bibmovel.utils.ConnectionFactory;

import java.sql.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Created by vinibrenobr11 on 24/06/2020 at 16:38
 */
public class SessaoController {

    private final Connection conn;

    public SessaoController() {
        conn = ConnectionFactory.getConnection();
    }

    public Usuario validate(Sessao sessao) throws SQLException {

        PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT u.usuario, u.nome, u.email FROM Sessao s JOIN Usuario u ON s.id_usuario = u.id " +
                        "WHERE s.id = ? AND s.id_usuario = ? AND s.hash_code = ? AND s.data_inicio = ? " +
                        "AND s.device_uuid = ?"
        );

        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(sessao.getDataInicio());
        ZonedDateTime i = Instant.from(ta).atZone(ZoneId.systemDefault());
        Date date = Date.from(i.toInstant());

        preparedStatement.setInt(1, sessao.getId());
        preparedStatement.setInt(2, sessao.getIdUsuario());
        preparedStatement.setString(3, sessao.getHashCode());
        preparedStatement.setTimestamp(4, new Timestamp(date.getTime()));
        preparedStatement.setString(5, sessao.getDeviceUUID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Usuario(rs.getString(1), rs.getString(2), rs.getString(3));
        }

        return null;
    }
}
