# Projeto: Sistema de Gerenciamento de Biblioteca Digital
Objetivo do Projeto
Desenvolver uma aplicação Java capaz de gerenciar livros e usuários em uma biblioteca digital. O sistema permitirá cadastrar livros e usuários, fazer empréstimos e devoluções, além de salvar e recuperar informações em arquivos de texto. Durante o desenvolvimento, será aplicado conceitos de:

* Exceções (tratamento e personalizadas)

* Serialização e Arquivos (salvar e carregar dados)

* Generics e Comparable (ordenar coleções de forma flexível)

* Programação Funcional (Streams e Lambdas)

## Funcionalidades do Sistema
* Gestão de Livros:

a. Cadastrar novo livro (título, autor, ano de publicação).

b. Listar todos os livros (mostrando também se está disponível ou emprestado)

c. Buscar livro por título (case insensitive, usando Stream).

d. Ordenar livros por título ou ano (usando Comparable ou Comparator com lambda).

* Gestão de Usuários:

a. Cadastrar novo usuário (nome, email).

b. Listar usuários.

* Empréstimos:

a. Realizar empréstimo (ligar usuário a livro: qual o titulo do livro que quer pegar emprestado e qual

b. usuário está pegando emprestado).

c. Devolver livro.

## Recursos Técnicos e Conceituais
➡ Exceptions:

* Criar uma exceção personalizada (LivroIndisponivelException) para tentar emprestar livro já

emprestado.

* Uso de blocos try-catch-finally para operações de IO e fluxo principal.

➡ Arquivos:

* Criar arquivo .dat para persistir os livros e os usuários.

* Implementar serialização dos objetos Livro e Usuário.

* Métodos para salvar e carregar dados do sistema automaticamente.

➡ Generics:

* Utilizar collections genéricas (List<Livro>, List<Usuario>) para armazenar os dados em memória.

➡ Comparable ou Comparator:

* Livros devem ser ordenáveis por título.

➡ Programação Funcional / Streams:

* Listar apenas livros de determinado autor usando filtro (filter).

* Buscar usuários com email específico usando map e filter.

* Ordenar livros por ano com sorted e expressões lambda.

* Agrupar livros por autor utilizando collectors.
