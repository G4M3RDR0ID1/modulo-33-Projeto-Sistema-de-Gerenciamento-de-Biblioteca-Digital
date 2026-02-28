package main.java.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Serializable {

    private String nome;
    private String email;
    private List<Livro> livros;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.livros = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(Livro livro){
        livros.remove(livro);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Usuario usuario = (Usuario) object;
        return Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
