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

HTTP GET → http://localhost:8080/usuarios

**EX:. Response:** 
{
        "cpf": 98765432111,
        "nome": "Bruce Dickinson",
        "dataNascimento": "1950-02-15",
        "qtdEmprestimos": 0
    }


### Cadastro de Usuarios
Rota responsável pelo cadastro de usuarios.
Deve retornar os dados atualizados do usuario cadastrado.

HTTP POST → http://localhost:8080/usuarios

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


### Edição de Usuario
Rota responsável pela edição de usuarios.
Deve retornar os dados atualizado do usuario Editado.

HTTP PUT → http://localhost:8080/usuarios/{cpf}


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
