# TaskFlow API

TaskFlow API é uma API REST desenvolvida com Java e Spring Boot com foco em gerenciamento de tarefas para pequenos times e projetos.

A proposta do projeto é evoluir além de uma simples “lista de tarefas”, buscando simular um backend mais próximo de aplicações reais utilizadas por equipes no dia a dia, incluindo organização de tarefas, estrutura escalável e boas práticas de desenvolvimento backend.

## Objetivos do projeto

- Praticar desenvolvimento backend com Java e Spring Boot
- Evoluir conhecimentos em APIs REST
- Aplicar organização em camadas (controller, service, repository e entity)
- Trabalhar com versionamento utilizando Git e GitHub
- Construir um projeto sólido para portfólio e preparação para estágio

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- H2 Database
- Hibernate
- Lombok
- Springdoc OpenAPI (Swagger)

## Funcionalidades atuais

- Inicialização da aplicação Spring Boot
- Integração com banco H2 em memória
- Configuração do Spring Data JPA
- Criação das entidades:
  - Status
  - Categoria
  - Usuario
  - Task
- Relacionamentos entre entidades utilizando `@ManyToOne`
- Criação de repositories com `CrudRepository`
- Endpoints REST:
  - `/tasks`
  - `/usuarios`
- Integração com Swagger/OpenAPI
- Estrutura backend organizada em camadas
- Integração com GitHub

- ## Próximos passos

- Corrigir endpoints `/categorias` e `/status`
- Corrigir reconhecimento completo dos repositories pelo Spring
- Implementar operações POST, PUT e DELETE
- Cadastro de dados no banco
- Validação de dados
- Tratamento global de exceções
- Melhorar documentação da API
- Sistema de autenticação e usuários

## Autores

- Cauã Monteiro
- João 
