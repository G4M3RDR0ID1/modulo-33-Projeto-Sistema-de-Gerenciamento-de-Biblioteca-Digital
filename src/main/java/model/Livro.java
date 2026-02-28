package main.java.model;

import java.io.Serializable;
import java.util.Objects;

public class Livro implements Serializable, Comparable<Livro> {

    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean emprestado;

    public Livro(String titulo, String autor, int anoPublicacao, boolean emprestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.emprestado = emprestado;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Livro livro = (Livro) object;
        return anoPublicacao == livro.anoPublicacao && Objects.equals(titulo, livro.titulo) && Objects.equals(autor, livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, anoPublicacao);
    }

    @Override
    public String toString() {
        return "Titulo do livro: " + getTitulo()
                + " - Autor: " + getAutor()
                + " - Ano de Publicação: " + "(" + getAnoPublicacao() + ")"
                + " - Livro esta esprestado? " + isEmprestado();
    }

    @Override
    public int compareTo(Livro outro) {
        return this.titulo.compareToIgnoreCase(outro.titulo);
    }
}
