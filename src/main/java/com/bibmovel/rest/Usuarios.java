package com.bibmovel.rest;

import com.bibmovel.dao.UsuarioDAO;
import com.bibmovel.entidades.Usuario;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by vinibrenobr11 on 16/10/18 at 19:03
 */
@Path("/usuario")
public class Usuarios {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Usuario usuario) {

        if (usuario.getLogin() == null)
            return Response.noContent().build();


        try {
            UsuarioDAO dao = new UsuarioDAO();

            if (dao.add(usuario))
                return Response.ok(usuario).build();
            else
                return Response.status(409).build();

        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) {

        try {
            UsuarioDAO dao = new UsuarioDAO();

            usuario = dao.login(usuario);

            if (usuario != null)
                return Response.ok(usuario).build();
            else
                return Response.status(404).build();

        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/google")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String verifyGoogleAccount(Usuario google) {

        try {
            UsuarioDAO dao = new UsuarioDAO();
            return new Gson().toJson(dao.verify(google));
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
