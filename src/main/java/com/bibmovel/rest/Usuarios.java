package com.bibmovel.rest;

import com.bibmovel.dao.UsuarioDAO;
import com.bibmovel.entidades.Usuario;

import javax.ws.rs.*;
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

       UsuarioDAO dao = new UsuarioDAO();

       try {

           if (dao.add(usuario))
               return Response.ok(usuario).build();
           else
               return Response.status(409).build();

       } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
           e.printStackTrace();
           return Response.serverError().build();
       }
   }

   @POST
   @Path("/login")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response login(Usuario usuario) {

       UsuarioDAO dao = new UsuarioDAO();

       try {

           usuario = dao.login(usuario);

           if (usuario != null)
               return Response.ok(usuario).build();
           else
               return Response.status(404).build();

       } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
           e.printStackTrace();
           return Response.serverError().build();
       }
   }
}
