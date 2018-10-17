package com.bibmovel.entidades;

import java.util.List;

public class Livro {

    private String titulo;
    private String isbn;
    private String nomeArquivo;
	private String genero;
    private Editora editora;
    private float classificacaoMedia;
    private List<Autor> autores;

    public Livro() {}

    public Livro(String titulo, String isbn, String nomeArquivo, String genero) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.nomeArquivo = nomeArquivo;
        this.genero = genero;
    }

    public Livro(String titulo, float classificacaoMedia) {
        this.titulo = titulo;
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

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public float getClassificacaoMedia() {
        return classificacaoMedia;
    }

    public void setClassificacaoMedia(float classificacaoMedia) {
        this.classificacaoMedia = classificacaoMedia;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
