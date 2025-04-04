# Banco de Dados MySQL - Sistema CRUD de Empresas e Fornecedores

Este banco de dados foi projetado para suportar um sistema CRUD de Empresas e Fornecedores, além de gerenciar a relação entre empresas e seus fornecedores por meio de uma tabela intermediária. Ele é usado no backend desenvolvido em Spring Boot, e sua estrutura segue as melhores práticas de organização e normalização.

## 🗃️ Estrutura do Banco de Dados
Tabelas
O banco de dados contém as seguintes tabelas principais:

EMPRESA Armazena as informações das empresas cadastradas.

FORNECEDOR Armazena as informações dos fornecedores cadastrados.

EMPRESA_FORNECEDOR Relaciona empresas com seus fornecedores, representando uma relação muitos para muitos.

Tabela: EMPRESA
Campo	Tipo	Descrição
id	INT AUTO_INCREMENT	Identificador único da empresa.
cnpj	VARCHAR(20)	Cadastro Nacional de Pessoa Jurídica (CNPJ).
nome_fantasia	VARCHAR(255)	Nome fantasia da empresa.
cep	VARCHAR(20)	Código de Endereçamento Postal (CEP).
created_at	DATETIME	Data de criação do registro.
updated_at	DATETIME	Data da última atualização do registro.
Tabela: FORNECEDOR
Campo	Tipo	Descrição
id	INT AUTO_INCREMENT	Identificador único do fornecedor.
cnpj_cpf	VARCHAR(20)	Cadastro de Pessoa Física ou Jurídica (CPF ou CNPJ).
nome	VARCHAR(255)	Nome do fornecedor.
telefone	VARCHAR(20)	Telefone de contato.
email	VARCHAR(255)	E-mail do fornecedor.
rg	VARCHAR(20)	Registro Geral (RG).
data_nascimento	DATE	Data de nascimento do fornecedor.
cep	VARCHAR(20)	Código de Endereçamento Postal (CEP).
created_at	DATETIME	Data de criação do registro.
updated_at	DATETIME	Data da última atualização do registro.
Tabela: EMPRESA_FORNECEDOR
Campo	Tipo	Descrição
id	INT AUTO_INCREMENT	Identificador único.
empresa_id	INT	Identificador da empresa relacionada.
fornecedor_id	INT	Identificador do fornecedor relacionado.
Chaves:

empresa_id é uma chave estrangeira que referencia EMPRESA(id).

fornecedor_id é uma chave estrangeira que referencia FORNECEDOR(id).

## 📄 SQL para Criação das Tabelas
Script de Criação
sql
CREATE TABLE EMPRESA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cnpj VARCHAR(20) NOT NULL UNIQUE,
    nome_fantasia VARCHAR(255) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE FORNECEDOR (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cnpj_cpf VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(255),
    rg VARCHAR(20),
    data_nascimento DATE,
    cep VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE EMPRESA_FORNECEDOR (
    id INT AUTO_INCREMENT PRIMARY KEY,
    empresa_id INT NOT NULL,
    fornecedor_id INT NOT NULL,
    FOREIGN KEY (empresa_id) REFERENCES EMPRESA(id),
    FOREIGN KEY (fornecedor_id) REFERENCES FORNECEDOR(id)
);

## 🎯 Finalidade do Banco de Dados
Gerenciar o cadastro e edição de empresas e fornecedores.

Permitir a associação entre empresas e seus fornecedores, possibilitando a criação de relações muitos para muitos.

Facilitar a integração com o backend em Spring Boot.

## 🔧 Configuração e Conexão
Certifique-se de configurar o arquivo application.properties no backend para estabelecer a conexão com este banco de dados:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true