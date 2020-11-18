package com.bibmovel.models;

import java.io.Serializable;

public class Livro implements Serializable {

    private Integer id;
    private String titulo;
    private String isbn;
    private String nomeArquivo;
    private String genero;
    private Short anoPublicacao;
    private String editora;
    private Float classificacaoMedia;
    private String autor;
    private Integer idUsuario;
    
    public Livro() {
    }

    public Livro(Integer id, String titulo, String isbn, String nomeArquivo, String genero, Short anoPublicacao, String editora, Float classificacaoMedia, String autor, Integer idUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.nomeArquivo = nomeArquivo;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.classificacaoMedia = classificacaoMedia;
        this.autor = autor;
        this.idUsuario = idUsuario;
    }

    public Livro(int id, String titulo, String nomeArquivo, float classificacaoMedia) {
        this.id = id;
        this.titulo = titulo;
        this.nomeArquivo = nomeArquivo;
        this.classificacaoMedia = classificacaoMedia;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNomeArquivo() {
        return this.nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Short getAnoPublicacao() {
        return this.anoPublicacao;
    }

    public void setAnoPublicacao(Short anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getEditora() {
        return this.editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Float getClassificacaoMedia() {
        return this.classificacaoMedia;
    }

    public void setClassificacaoMedia(Float classificacaoMedia) {
        this.classificacaoMedia = classificacaoMedia;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}