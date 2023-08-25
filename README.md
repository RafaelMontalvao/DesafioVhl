# DesafioVhl

## Introdução

Criar uma API em Java, Spring Boot e Maven para o gerenciamento de uma biblioteca. A
API deve possuir as seguintes funcionalidades:

● Possibilidade de adicionar/editar/excluir livros e usuários;

● Controle de empréstimo e devolução de livros;

● Limitar a quantidade de empréstimos para apenas 2 livros por usuário;

● Capacidade de pesquisar um livro por título, autor;

● Capacidade de pesquisar um usuário pelo nome.

## Tecnologias & Versões
- **Java** - versão 17;
- **Spring Boot** - versão3.2.0-SNAPSHOT;
- **Maven** - versão 4.0.0;
- **ModelMapper** - versão 3.1.0;
- **Banco de dados SQL embarcado (H2)**;- 


# Rotas & Funcionalidades
## Consulta de Usuarios
Rota responsável pela consulta de todos os usuarios cadastrados.

Deve retornar os dados atualizados de todos os usuarios.

**HTTP GET** → http://localhost:8080/usuarios

**EX:. Response:** 
{
        "cpf": 98765432111,
        "nome": "Bruce Dickinson",
        "dataNascimento": "1950-02-15",
        "qtdEmprestimos": 0
    }

## Consulta de Usuarios por nome
Rota responsável pela consulta de usuarios por nome.

Deve retornar os dados atualizados de todos os usuarios com aquele nome.

**HTTP GET** → http://localhost:8080/usuarios/{nome}

**EX:. Rota:** http://localhost:8080/usuarios/Bruce Dickinson

**EX:. Response:** 
{
        "cpf": 98765432111,
        "nome": "Bruce Dickinson",
        "dataNascimento": "1950-02-15",
        "qtdEmprestimos": 0
    }


## Cadastro de Usuarios
Rota responsável pelo cadastro de usuarios.

Deve retornar os dados atualizados do usuario cadastrado.

**HTTP POST** → http://localhost:8080/usuarios

**EX:. Request:** 
{
 "cpf": 12345678902,
 "nome": "Rafael Brito",
 "dataNascimento": "1900-10-31"
}

**EX:. Response:**
{
    "cpf": 12345678902,
    "nome": "Rafael Brito",
    "dataNascimento": "1900-10-31",
    "qtdEmprestimos": 0
}


## Edição de Usuario
Rota responsável pela edição de usuarios por CPF.

Deve retornar os dados atualizado do usuario Editado.

**HTTP PUT** → http://localhost:8080/usuarios/{cpf}


**EX:. Rota:** http://localhost:8080/usuarios/12345678902

**EX:. Request:** 
{
  "nome": "Joao Pedro",
 "dataNascimento": "1915-10-31"
}

**EX:. Response:**
{
    "cpf": 12345678902,
    "nome": "Joao Pedro",
    "dataNascimento": "1915-10-31",
    "qtdEmprestimos": 0
}

## Exclusão de Usuario
Rota responsável pela exclusao do usuario por CPF.

Deve retornar 204 No Content.

**HTTP DELETE** → http://localhost:8080/usuarios/{cpf}

**EX:. Rota:** http://localhost:8080/usuarios/12345678902

## Consulta de Livros
Rota responsável pela consulta de todos os livros cadastrados.
Deve retornar os dados atualizados de todos os livros.

**HTTP GET** → http://localhost:8080/livros

**EX:. Response:** 
{
        "isbn": "978-0548952610",
        "titulo": "Princípios Matemáticos de Filosofia Natural",
        "autores": "Isaac Newton",
        "disponivel": true
    }

## Consulta de Livros por Autor
Rota responsável pela consulta de todos os livros cadastrados por autor.
Deve retornar os dados atualizados de todos os livros cadastrados por autor.

**HTTP GET** → http://localhost:8080/livros/autor/{autor}

**EX:. Rota:** http://localhost:8080/livros/autor/isaac

**EX:. Response:** 
{
        "isbn": "978-0548952610",
        "titulo": "Princípios Matemáticos de Filosofia Natural",
        "autores": "Isaac Newton",
        "disponivel": true
    }

## Consulta de Livros por Titulo
Rota responsável pela consulta de todos os livros cadastrados por Titulo.

Deve retornar os dados atualizados de todos os livros cadastrados por Titulo.

**HTTP GET** → http://localhost:8080/livros/titulo/{titulo}

**EX:. Rota:** http://localhost:8080/livros/titulo/Guia

**EX:. Response:** 
{
        "isbn": "978-8599296578",
        "titulo": "O Guia do Mochileiro das Galáxias",
        "autores": "Douglas Adams",
        "disponivel": true
    }

## Cadastro de Livros
Rota responsável pelo cadastro de livros.

Deve retornar os dados atualizados do livro cadastrado.

**HTTP POST** → http://localhost:8080/livros

**EX:. Request:** 
{
    "isbn": "121554-fv",
    "titulo" : "guia",
    "autores" : "isaac",
    "disponivel": true
}

**EX:. Response:**
{
    "isbn": "121554-fv",
    "titulo" : "guia",
    "autores" : "isaac",
    "disponivel": true   }

## Edição de livros por 
Rota responsável pela edição de livros por ISBN.

Deve retornar os dados atualizado do livro Editado.

**HTTP PUT** → http://localhost:8080/usuarios/{isbn}


**EX:. Rota:** http://localhost:8080/livros/121554-fv

**EX:. Request:**
{
    "titulo": "Cora Carolina",
    "autores": "pedro Carvalho",
    "disponivel": true
}

**EX:. Response:**
{
    "isbn": "121554-fv",
    "titulo": "Cora Carolina",
    "autores": "pedro Carvalho",
    "disponivel": true
}

## Exclusão de livros
Rota responsável pela exclusao do livro por ISBN.

Deve retornar 204 No Content.

**HTTP DELETE** → http://localhost:8080/usuarios/{isbn}

**EX:. Rota:** http://localhost:8080/livros/121554-fv


## Cadastro de Emprestimos
Rota responsável pelo cadastro de Emprestimos de livro

Retorna os dados do emprestimo cadastrado

**HTTP POST** → http://localhost:8080/emprestimos

**EX:. Request:** 
{
    "isbn": "978-8576082675",
    "cpf": 98765432111
}

**EX:. Response:**
{
    "id": 1,
    "isbn": "978-8576082675",
    "titulo": null,
    "cpf": 98765432111,
    "nome": null,
    "dataHoraEmprestimo": "2023-08-24T02:17:50.7545554"
}

## Exclusão de emprestimos
Rota responsável pela exclusao de empresitmos por ID.

Deve retornar 204 No Content.

**HTTP DELETE** → (http://localhost:8080/emprestimos/{id}

**EX:. Rota:** http://localhost:8080/emprestimos/1

## Consulta de Emprestimos
Rota responsável pela consulta de todos os emprestimos cadastrados.

Deve retornar os dados atualizados de todos os emprestimos.

**HTTP GET** → http://localhost:8080/emprestimos

**EX:. Response:** 
{
        "id": 2,
        "isbn": "978-8576082675",
        "titulo": "Clean Code",
        "cpf": 98765432111,
        "nome": "Bruce Dickinson",
        "dataHoraEmprestimo": "2023-08-24T02:23:49.431694"
    }









