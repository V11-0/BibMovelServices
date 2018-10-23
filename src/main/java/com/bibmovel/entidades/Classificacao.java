package com.bibmovel.entidades;

public class Classificacao {

    private Usuario usuario;
    private Livro livro;
    private float classificacao;
    private String comentario;

    public Classificacao() {}

    public Classificacao(Usuario usuario, Livro livro, float classificacao, String comentario) {
        this.usuario = usuario;
        this.livro = livro;
        this.classificacao = classificacao;
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public float getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(float classificacao) {
        this.classificacao = classificacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
