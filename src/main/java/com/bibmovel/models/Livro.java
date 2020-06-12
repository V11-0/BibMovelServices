package com.bibmovel.models;

import java.io.Serializable;

public class Livro implements Serializable {

    private String titulo;
    private String isbn;
    private String nomeArquivo;
    private String genero;
    private Short anoPublicacao;
    private String editora;
    private Float classificacaoMedia;
    private String autor;

    public Livro() {
    }

    public Livro(String titulo) {
        this.titulo = titulo;
    }

    public Livro(String titulo, String nomeArquivo, String autor, Float classificacaoMedia) {
        this.titulo = titulo;
        this.nomeArquivo = nomeArquivo;
        this.autor = autor;
        this.classificacaoMedia = classificacaoMedia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Short getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Short anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Float getClassificacaoMedia() {
        return classificacaoMedia;
    }

    public void setClassificacaoMedia(Float classificacaoMedia) {
        this.classificacaoMedia = classificacaoMedia;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
