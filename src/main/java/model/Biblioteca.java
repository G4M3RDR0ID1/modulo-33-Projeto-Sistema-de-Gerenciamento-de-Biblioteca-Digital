package main.java.model;

import main.java.exception.LivroIndisponivelException;
import main.java.exception.LivroJaCadastradoException;
import main.java.exception.UsuarioDuplicadoException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca implements Serializable {

    private List<Livro> livros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void salvarDados(String caminho) {
        try {

            File arquivo = new File(caminho);
            File pasta = arquivo.getParentFile();

            if (pasta != null && !pasta.exists()) {
                pasta.mkdirs(); // cria a pasta automaticamente
            }

            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(arquivo));

            oos.writeObject(this);
            oos.close();

            System.out.println("Dados salvos com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static Biblioteca carregarDados(String caminho) {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(caminho))) {

            return (Biblioteca) ois.readObject();

        } catch (Exception e) {
            System.out.println("Arquivo não encontrado. Criando nova biblioteca.");
            return new Biblioteca();
        }
    }

    public void cadastrarUsuario(Usuario usuario) throws UsuarioDuplicadoException {
        if (usuarios.contains(usuario)) {
            throw new UsuarioDuplicadoException("Usuário já cadastrado!");
        }
        usuarios.add(usuario);
    }

    public void realizarEmprestimo(String email, String titulo) {

        Usuario usuario = buscarUsuarioPorEmail(email);
        Livro livro = buscarLivroPorTitulo(titulo);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (livro.isEmprestado()) {
            throw new LivroIndisponivelException("Livro já está emprestado.");
        }

        usuario.adicionarLivro(livro);
        livro.setEmprestado(true);

        System.out.println("Empréstimo realizado com sucesso!");
    }

    public void devolverEmprestimo(String email, String titulo, String autor, int anoPublicacao) {

        Usuario usuario = buscarUsuarioPorEmail(email);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Livro livro = livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo)
                        && l.getAutor().equalsIgnoreCase(autor)
                        && l.getAnoPublicacao() == anoPublicacao)
                .findFirst()
                .orElse(null);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        usuario.removerLivro(livro);
        livro.setEmprestado(false);

        System.out.println("Livro devolvido com sucesso!");
    }

    public void listarLivrosEmprestados(String email){
        Usuario usuario = buscarUsuarioPorEmail(email);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (usuario.getLivros().isEmpty()){
            System.out.println("Usuario não esta com nenhum livro emprestado");
            return;
        }

        usuario.getLivros().forEach(System.out::println);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        return livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public void cadastrarLivro(String titulo, String autor, int anoPublicacao) throws LivroJaCadastradoException {
        boolean livroCadastrado = livroJaEstaCadastrado(titulo, autor, anoPublicacao);
        if (livroCadastrado) {
            throw new LivroJaCadastradoException("main.java.model.Livro ja cadastrado no sistema!");
        }
        livros.add(new Livro(titulo, autor, anoPublicacao, false));
    }

    public boolean livroJaEstaCadastrado(String titulo, String autor, int anoPublicacao) {
        return livros.stream()
                .anyMatch(livro ->
                        livro.getTitulo().equalsIgnoreCase(titulo) &&
                                livro.getAutor().equalsIgnoreCase(autor) && livro.getAnoPublicacao() == anoPublicacao);
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum main.java.model.Livro cadastrado!");
        }
        else {
            System.out.println("Livros Cadastrados: \n");
            for (Livro l : livros) {
                System.out.println(l);
            }
        }
        System.out.println();
    }

    public void listarUsuarios(){
        if (usuarios.isEmpty()){
            System.out.println("Nenhum Usuario Cadastrado!");
        } else {
            System.out.println("Usuarios Cadastrados: \n");
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }
    }

    public void agruparLivrosPorAutor() {

        Map<String, List<Livro>> agrupado =
                livros.stream()
                        .collect(Collectors.groupingBy(Livro::getAutor));

        agrupado.forEach((autor, lista) -> {
            System.out.println("\nAutor: " + autor);
            lista.forEach(System.out::println);
        });
    }

    public void ordenarLivrosPorTitulo(){
        Collections.sort(livros);
        listarLivros();
    }

    public void ordenarLivrosPorAno() {

        livros.stream()
                .sorted(Comparator.comparingInt(Livro::getAnoPublicacao))
                .forEach(System.out::println);
    }

    public void buscarLivroPorAutor(String tituloDoLivro){

        List<Livro> livroEncontrado = livros.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(tituloDoLivro)).toList();

        if (livroEncontrado.isEmpty()){
            System.out.println("Não temos esse livro na biblioteca.");
        } else {
            livroEncontrado.forEach(livro ->{
                if (livro.isEmprestado()){
                    System.out.println("Livro esta emprestado no momento");
                }
                else{
                    System.out.println(livro);
                }
            } );
        }
    }
}
