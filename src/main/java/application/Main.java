package main.java.application;

import main.java.exception.LivroIndisponivelException;
import main.java.model.Biblioteca;
import main.java.model.Usuario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = Biblioteca.carregarDados("data/biblioteca.dat");
        boolean loopMenu = true;


        System.out.println("Bem vindo a biblioteca");
        while (loopMenu) {
            System.out.println("Escolha uma opção abaixo:\n" +
                    "1 - Cadastrar novo livro.\n" +
                    "2 - Listar todos os livros.\n" +
                    "3 - Buscar livro por titulo.\n" +
                    "4 - Ordenar livros\n" +
                    "5 - Cadastrar novo usuario(Nome, Email).\n" +
                    "6 - Listar Usuarios.\n" +
                    "7 - Realizar Emprestimo.\n" +
                    "8 - Livros que o usuario esta pegando emprestado.\n" +
                    "9 - Devolver Livro.\n" +
                    "10 - Sair.");

            int opcaoMenu = sc.nextInt();

            switch (opcaoMenu) {
                case 1:
                    sc.nextLine();

                    System.out.println("Digite o titulo do livro:");
                    String titulo = sc.nextLine();
                    System.out.println("Digite o nome do autor:");
                    String autor = sc.nextLine();
                    System.out.println("Digite o ano de publicação do livro:");
                    int anoPublicacao = sc.nextInt();

                    biblioteca.cadastrarLivro(titulo, autor, anoPublicacao);

                    break;

                case 2:

                    biblioteca.listarLivros();

                    break;

                case 3:
                    sc.nextLine();

                    System.out.println("Digite o titulo do livro: ");
                    String tituloProcurado = sc.nextLine();

                    biblioteca.buscarLivroPorAutor(tituloProcurado);

                    break;

                case 4:
                    System.out.println("Digite o tipo de ordenaçao que deseja: \n" +
                            "1 - Ordenar por Titulo (A-Z).\n" +
                            "2 - Ordenar por Ano Publicação (Menor-Maior).\n" +
                            "3 - Agrupar livros por Autor.\n");

                    int opcaoOrdenacao = sc.nextInt();
                    switch (opcaoOrdenacao) {
                        case 1 :
                            biblioteca.ordenarLivrosPorTitulo();
                            break;
                        case 2:
                            biblioteca.ordenarLivrosPorAno();
                            break;
                        case 3:
                            biblioteca.agruparLivrosPorAutor();
                            break;
                        default:
                            System.out.println("Opção desejada nao se encontra no menu.");
                    }
                    break;
                case 5:
                    sc.nextLine();

                    System.out.println("Digite o nome do usuario: ");
                    String nomeUsuario = sc.nextLine();
                    System.out.println("Digite o email: ");
                    String emailUsuario = sc.nextLine();

                    biblioteca.cadastrarUsuario(new Usuario(nomeUsuario, emailUsuario));
                    break;

                case 6:

                    biblioteca.listarUsuarios();

                    break;

                case 7:
                    sc.nextLine();

                    System.out.println("Digite o titulo do livro:");
                    String nomeLivro = sc.nextLine();
                    System.out.println("Digite o email do Usuario que vai pegar o livro emprestado:");
                    String emailUser= sc.nextLine();

                    try {
                        biblioteca.realizarEmprestimo(emailUser, nomeLivro);
                    } catch (LivroIndisponivelException e) {
                        System.out.println(e.getMessage());
                    }


                    break;

                case 8:
                    sc.nextLine();

                    System.out.println("Digite o email do usuario: ");
                    emailUser = sc.nextLine();

                    biblioteca.listarLivrosEmprestados(emailUser);
                    break;

                case 9:
                    sc.nextLine();

                    System.out.println("Digite o email do usuario: ");
                    emailUser = sc.nextLine();

                    System.out.println("Digite o titulo do livro:");
                    titulo = sc.nextLine();
                    System.out.println("Digite o nome do autor:");
                    autor = sc.nextLine();
                    System.out.println("Digite o ano de publicação do livro:");
                    anoPublicacao = sc.nextInt();

                    biblioteca.devolverEmprestimo(emailUser, titulo, autor, anoPublicacao);
                    break;

                case 10:
                    biblioteca.salvarDados("data/biblioteca.dat");
                    System.out.println("Saindo do Sistema!");
                    loopMenu = false;
                    break;

                default:
                    System.out.println("Numero Digitado não corresponde as opçoes do menu, Digite novamente!");
            }

        }

    }

}