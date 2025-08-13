# 🚌 Vou de Busão API

A Vou de Busão API é uma aplicação desenvolvida com Spring Boot que simula o gerenciamento de excursões turísticas. Além de oferecer algumas funcionalidades como cadastro, consulta e atualizações, seu principal objetivo é **demonstrar a aplicação prática de Design Patterns** em um projeto real.

---

## 🚀 Funcionalidades

A API oferece os seguintes recursos:

### **Gerenciamento de Excursões**: 
- criação com destino, data e preço
- listar excursões
- consulta por destino 
- consulta por id 
- atualização
- exclusão


### **Gerenciamento de Passageiros**: 
- cadastro
- consulta por id ou nome
- listar passageiros
- atualização
- exclusão

### **Gerenciamento de Reservas**: 
- criação de reservas
- consulta por id e status
- listar reservas
- atualização de status
- exclusão

---

## 🧠 Padrões de Projeto Utilizados

- Repository Pattern
- Exception Handling / Custom Exception Pattern
- RESTful Controller (Controller Pattern)
- Optional Pattern

---

## 🧠 Conhecimentos Técnicos e Conceituais Trabalhados

1. Programação Orientada a Objetos (POO)
2. Desenvolvimento de APIs RESTful
3. Spring Framework / Spring Boot
4. Persistência de Dados com JPA/Hibernate
5. Tratamento de Exceções
6. Boas Práticas de Arquitetura
7. Docker
8. Documentação com Swagger

---

## 🏁 Como Executar o Projeto

Siga os passos abaixo para subir a API localmente com o banco de dados via Docker Compose e acessar a documentação da API.

---

### 📦 Pré-requisitos

- Java 17+
- Gradle (ou use o wrapper `./gradlew`)
- Docker e Docker Compose

### 🐳 1. Subir o Banco de Dados com Docker Compose

O projeto utiliza um banco de dados relacional MySQL. Para iniciar o banco:

docker-compose up -d

Isso irá iniciar o container do banco de dados definido no arquivo docker-compose.yml.

### ⚙️ 2. Executar a API com Gradle

Com o banco rodando, execute a aplicação:

./gradlew bootRun

Ou, se tiver o Gradle instalado globalmente:

gradle bootRun

A API será iniciada em:

http://localhost:8081

### 📖 3. Acessar a Documentação da API (OpenAPI)

A documentação interativa está disponível via Swagger UI:

http://localhost:8081/swagger-ui/index.html

Você poderá visualizar todos os endpoints disponíveis, testar requisições e entender os modelos de dados.
