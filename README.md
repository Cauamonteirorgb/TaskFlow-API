# TaskFlow API

TaskFlow API é uma API REST desenvolvida com Java e Spring Boot com foco em gerenciamento de tarefas para pequenos times e projetos.

A ideia do projeto é ir além de uma simples “to-do list”, buscando simular um backend mais próximo de aplicações reais utilizadas no mercado, com organização em camadas, validações, documentação, testes automatizados e boas práticas de desenvolvimento backend.

O projeto está sendo desenvolvido como parte da disciplina de ADS, mas também com foco em aprendizado prático, construção de portfólio e preparação para estágio na área de desenvolvimento backend.

---

## Objetivos do projeto

- Praticar desenvolvimento backend com Java e Spring Boot
- Evoluir conhecimentos em APIs REST
- Aplicar arquitetura em camadas
- Trabalhar com persistência de dados utilizando JPA/Hibernate
- Implementar validações e tratamento de exceções
- Aprender testes automatizados no ecossistema Spring
- Utilizar Git e GitHub em um fluxo de desenvolvimento real
- Construir um projeto sólido para portfólio e evolução profissional

---

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Lombok
- Bean Validation
- Springdoc OpenAPI (Swagger)
- JUnit 5
- Mockito
- JaCoCo

---

## Estrutura do projeto

O projeto segue uma organização em camadas para separar responsabilidades e facilitar manutenção, escalabilidade e testes.

```txt
controller  -> endpoints da API
service     -> regras de negócio
repository  -> acesso ao banco de dados
entity      -> entidades JPA
dto         -> transferência de dados
exception   -> tratamento global de erros
