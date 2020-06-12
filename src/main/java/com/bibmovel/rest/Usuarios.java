package com.bibmovel.rest;

import com.bibmovel.controller.UsuarioController;
import com.bibmovel.models.Usuario;

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

        if (usuario.getUsuario() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            UsuarioController controller = new UsuarioController();

            if (controller.add(usuario)) {
                return Response.ok(usuario).build();
            } else {
                return Response.status(Response.Status.CONFLICT).build();
            }

        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) {

        try {
            UsuarioController dao = new UsuarioController();

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
    public Response verifyGoogleAccount(Usuario google) {

        try {
            UsuarioController dao = new UsuarioController();
            return Response.ok(dao.verify(google)).build();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }
}
