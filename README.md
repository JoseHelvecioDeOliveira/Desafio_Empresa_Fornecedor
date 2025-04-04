# Desafio Full-Stack: Sistema CRUD de Empresas e Fornecedores

Este projeto é a solução do Desafio Full-Stack, cujo objetivo é avaliar habilidades de desenvolvimento, estética e técnicas. O sistema foi desenvolvido com Spring Boot no backend e Angular no frontend, 
implementando um completo CRUD para gerenciar Empresas e Fornecedores.

## 📝 Descrição do Desafio

### O sistema foi desenvolvido para atender aos seguintes requisitos:

## Entidades Base:

Empresa:

CNPJ.

Nome Fantasia.

CEP.

Fornecedor:

CNPJ ou CPF.

Nome.

E-mail.

CEP.

## Requisitos Técnicos:

CRUD Completo para Empresas e Fornecedores (Backend e Frontend).

Relacionamento Many-to-Many:

Uma empresa pode ter vários fornecedores.

Um fornecedor pode trabalhar para várias empresas.

## Validação Única:

CNPJ e CPF devem ser valores únicos no sistema.

## Validações Regras de Negócio:

Cadastro de fornecedor pessoa física exige RG e data de nascimento.

Não permitir cadastro de fornecedor pessoa física menor de idade caso a empresa esteja localizada no Paraná (baseado no CEP).

Filtros e Pesquisa:

Listagem de fornecedores com filtros por Nome e CPF/CNPJ.

## Validação de CEP:

Integração com a API http://cep.la/api para validação do CEP (tanto no backend quanto no frontend).

Flexibilidade no Modelo:

Possibilidade de adicionar novas colunas, classes, heranças e recursos, conforme necessidade.

Extras (Opcional):

Testes unitários.

Implementação de um Dockerfile para facilitar o deploy.

## 🚀 Tecnologias Utilizadas

Backend:
Java 17.

Spring Boot (incluindo Spring Data JPA e Spring Validation).

MySQL como banco de dados.

Hibernate para mapeamento objeto-relacional (ORM).

## Frontend:

Angular 15.

HTML5/CSS3 com design responsivo.

TypeScript com forte tipagem para segurança e escalabilidade.

## Integrações:
API externa: http://cep.la/api (para validação de CEP).

Banco de dados relacional: MySQL.

## 🛠️ Estrutura do Projeto
Backend (Spring Boot)
src/
├── main/
│   ├── java/
│   │   └── com.example.projeto/
│   │       ├── controller/      # Endpoints REST para Empresas e Fornecedores
│   │       ├── model/           # Entidades: Empresa, Fornecedor e EmpresaFornecedor
│   │       ├── repository/      # Interfaces JPA para acesso ao banco de dados
│   │       └── service/         # Regras de negócio e integrações (ex.: API de CEP)
│   ├── resources/
│       ├── application.properties  # Configurações do banco e servidor
│       ├── schema.sql               # Estrutura inicial do banco (opcional)
│       └── data.sql                 # Dados iniciais (opcional)
Frontend (Angular)
src/
├── app/
│   ├── components/
│   │   ├── empresa/              # CRUD de Empresas
│   │   ├── fornecedor/           # CRUD de Fornecedores
│   ├── services/
│   │   ├── empresa.service.ts    # Chamadas HTTP relacionadas às Empresas
│   │   ├── fornecedor.service.ts # Chamadas HTTP relacionadas aos Fornecedores
│   ├── models/
│   │   ├── empresa.model.ts      # Modelo Empresa
│   │   ├── fornecedor.model.ts   # Modelo Fornecedor
│   ├── app-routing.module.ts     # Configuração das rotas
│   ├── app.module.ts             # Configuração do módulo principal
│   └── app.component.ts          # Componente principal
## 📄 Endpoints Backend

## Empresas

Método	Endpoint	Descrição
POST	/empresas	Cria uma nova empresa.
GET	/empresas	Retorna todas as empresas.
PUT	/empresas/{cnpj}	Atualiza uma empresa existente.
DELETE	/empresas/{cnpj}	Remove uma empresa.
Fornecedores
Método	Endpoint	Descrição
POST	/fornecedores	Cria um novo fornecedor.
GET	/fornecedores	Retorna todos os fornecedores.
PUT	/fornecedores/{id}	Atualiza um fornecedor existente.
DELETE	/fornecedores/{id}	Remove um fornecedor.

## 🗃️ Estrutura do Banco de Dados

Tabelas
Tabela: EMPRESA
Campo	Tipo	Descrição
id	INT AUTO_INCREMENT	Identificador único da empresa.
cnpj	VARCHAR(20)	Cadastro Nacional de Pessoa Jurídica (CNPJ).
nome_fantasia	VARCHAR(255)	Nome fantasia da empresa.
cep	VARCHAR(20)	Código de Endereçamento Postal (CEP).
created_at	DATETIME	Data de criação.
updated_at	DATETIME	Data da última atualização.
Tabela: FORNECEDOR
Campo	Tipo	Descrição
id	INT AUTO_INCREMENT	Identificador único do fornecedor.
cnpj_cpf	VARCHAR(20)	CPF ou CNPJ.
nome	VARCHAR(255)	Nome do fornecedor.
email	VARCHAR(255)	E-mail do fornecedor.
cep	VARCHAR(20)	Código de Endereçamento Postal (CEP).
rg	VARCHAR(20)	Registro Geral (RG).
data_nascimento	DATE	Data de nascimento (se pessoa física).
created_at	DATETIME	Data de criação.
updated_at	DATETIME	Data da última atualização.
Tabela: EMPRESA_FORNECEDOR
Campo	Tipo	Descrição
id	INT AUTO_INCREMENT	Identificador único.
empresa_id	INT	FK: Identificador da empresa.
fornecedor_id	INT	FK: Identificador do fornecedor.

## 🔧 Instruções de Configuração
Configure o banco de dados MySQL com o script acima.

Atualize o arquivo application.properties no backend:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Certifique-se de que o backend está rodando antes de iniciar o frontend.

## No frontend, configure o arquivo environment.ts:

typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};

## 🖥️ Como Rodar
Backend:
No diretório do backend:

bash
mvn spring-boot:run
Frontend:
No diretório do frontend:

bash
npm install
ng serve

## 🎯 Desafios e Regras Implementadas
Relacionamento muitos para muitos entre empresas e fornecedores.

Validação de CPF/CNPJ para garantir unicidade.

Regra Regional:

Empresas do Paraná não podem cadastrar fornecedores pessoa física menores de idade.

Validação de CEP com API externa (http://cep.la/api).

Filtros por nome e CPF/CNPJ na listagem de fornecedores.

Design responsivo no frontend.

## 📦 Melhorias Futuras
Adicionar autenticação JWT.

Implementar testes unitários (Karma e Jasmine no frontend, JUnit no backend).

Criar Dockerfile para facilitar o deploy.

## 🤝 Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests. 🚀
