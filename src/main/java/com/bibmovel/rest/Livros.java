package com.bibmovel.rest;

import com.bibmovel.dao.LivroDAO;
import com.bibmovel.entidades.Livro;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by vinibrenobr11 on 11/10/18 at 23:46
 */

@Path("/livros")
public class Livros {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {

        LivroDAO dao = new LivroDAO();

        List<Livro> livros = dao.getAll();

        return new Gson().toJson(livros);
    }

    @GET
    @Path("/basic")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBasicInfo() {

        LivroDAO dao = new LivroDAO();

        List<Livro> livros = dao.getBasicInfo();

        return new Gson().toJson(livros);
    }
}
