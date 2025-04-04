# Projeto CRUD de Empresas e Fornecedores

Este projeto é um sistema CRUD (Create, Read, Update, Delete) desenvolvido em Java utilizando o framework Spring Boot e MySQL como banco de dados. O objetivo é gerenciar empresas e fornecedores, permitindo a criação, edição, listagem e exclusão dessas entidades.

## 🛠️ Estrutura do Projeto
O projeto está dividido nas seguintes camadas:

### Model: Contém as classes que representam as entidades do banco de dados.

### Repository: Contém as interfaces responsáveis por acessar e manipular o banco de dados.

### Service: Contém a lógica de negócios e serve de intermediária entre o controller e o repositório.

### Controller: Contém os endpoints da API para comunicação com o cliente.

## 🚀 Funcionalidades

## Empresas
Criar empresas.

Editar informações de empresas.

Listar todas as empresas cadastradas.

Excluir empresas.

## Fornecedores
Criar fornecedores.

Editar informações de fornecedores.

Listar todos os fornecedores cadastrados.

Excluir fornecedores.

## 🗂️ Estrutura de Pastas
src/
├── main/
│   ├── java/
│   │   └── com.example.projeto/
│   │       ├── controller/      # Controladores REST
│   │       ├── model/           # Entidades
│   │       ├── repository/      # Interfaces de repositórios
│   │       └── service/         # Regras de negócio
│   ├── resources/
│       ├── application.properties  # Configurações do projeto
│       ├── data.sql                 # Dados iniciais do banco (opcional)
│       └── schema.sql               # Estrutura do banco de dados (opcional)

## 🗃️ Banco de Dados
O projeto utiliza o banco de dados MySQL. Certifique-se de criar o banco de dados antes de iniciar o projeto.

Configuração do banco no application.properties:
properties

# Configurações de conexão com o MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

## 🖥️ Como Rodar o Projeto
Pré-requisitos:

Java 17 ou superior.

MySQL instalado e configurado.

Maven para gerenciamento de dependências.

Configurar o Banco de Dados:

Crie o banco de dados no MySQL com o nome desejado.

Ajuste as credenciais no arquivo application.properties.

Rodar o Projeto:

No terminal, execute o comando:

bash
mvn spring-boot:run
Acesse a aplicação pelo navegador ou cliente REST (como Postman) no endereço:

http://localhost:8080

## 📄 Endpoints da API

### Empresas

Método	Endpoint	Descrição
POST	/empresas	Cria uma nova empresa.
GET	/empresas	Retorna todas as empresas.
PUT	/empresas/{cnpj}	Atualiza uma empresa existente.
DELETE	/empresas/{cnpj}	Remove uma empresa.

## Fornecedores

Método	Endpoint	Descrição
POST	/fornecedores	Cria um novo fornecedor.
GET	/fornecedores	Retorna todos os fornecedores.
PUT	/fornecedores/{id}	Atualiza um fornecedor existente.
DELETE	/fornecedores/{id}	Remove um fornecedor.

## ✨ Tecnologias Utilizadas
Java 17

Spring Boot

Spring Data JPA

MySQL

Maven

## 🔧 Possíveis Melhorias Futuras
Implementar autenticação e autorização com Spring Security.

Adicionar logs utilizando Spring Boot Actuator.

Criar testes unitários e de integração com JUnit.

## 🤝 Contribuições
Sinta-se à vontade para contribuir com melhorias, sugestões ou correções.