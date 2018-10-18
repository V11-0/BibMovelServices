package com.bibmovel.rest;

import com.bibmovel.dao.UsuarioDAO;
import com.bibmovel.entidades.Usuario;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vinibrenobr11 on 16/10/18 at 19:03
 */
@Path("/usuarios")
public class Usuarios {

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response add(Usuario usuario) {

       if (usuario.getNome() == null)
           return Response.noContent().build();

       UsuarioDAO dao = new UsuarioDAO();
       dao.add(usuario);

       return Response.ok(usuario).build();
   }

   @GET
   @Consumes(MediaType.APPLICATION_JSON)
   public Response login(Usuario usuario) {

       UsuarioDAO dao = new UsuarioDAO();

       if (dao.login(usuario)) {
           return Response.accepted(usuario).build();
       }

       return Response.status(404).build();
   }
}
