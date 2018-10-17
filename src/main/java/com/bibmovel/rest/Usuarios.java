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
   @Produces(MediaType.APPLICATION_JSON)
   public Usuario add(Usuario usuario) {

       if (usuario.getNome() == null)
           throw new WebApplicationException(Response.Status.BAD_REQUEST);

       UsuarioDAO dao = new UsuarioDAO();
       dao.add(usuario);

       return null;
   }

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/{login}/{senha}")
   public String login(@PathParam("login") String login, @PathParam("senha") String senha) {

       UsuarioDAO dao = new UsuarioDAO();
       Usuario usuario = new Usuario(login, senha);

       if (dao.login(usuario)) {
           return new Gson().toJson(usuario);
       }

       return null;
   }
}
