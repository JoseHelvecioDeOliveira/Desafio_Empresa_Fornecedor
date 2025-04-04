# Frontend CRUD de Empresas e Fornecedores

Este é o frontend para o projeto CRUD de empresas e fornecedores, desenvolvido com Angular e integrado ao backend criado com Spring Boot. O objetivo é gerenciar o cadastro, edição, listagem e exclusão de empresas e fornecedores de forma eficiente.

## 🛠️ Estrutura do Projeto
O projeto está estruturado seguindo as melhores práticas do Angular, com uma separação clara das responsabilidades por meio de módulos e serviços.

## Estrutura de Pastas
src/
├── app/
│   ├── components/
│   │   ├── empresa/              # Componente para CRUD de empresas
│   │   ├── fornecedor/           # Componente para CRUD de fornecedores
│   ├── services/
│   │   ├── empresa.service.ts    # Serviço para chamadas relacionadas a empresas
│   │   ├── fornecedor.service.ts # Serviço para chamadas relacionadas a fornecedores
│   ├── models/
│   │   ├── empresa.model.ts      # Modelo da entidade Empresa
│   │   ├── fornecedor.model.ts   # Modelo da entidade Fornecedor
│   ├── app-routing.module.ts     # Configurações de rotas do Angular
│   ├── app.module.ts             # Configuração principal do Angular
│   └── app.component.ts          # Componente principal

## 🚀 Funcionalidades

### Empresas
Listar todas as empresas cadastradas.

Criar novas empresas.

Editar informações de empresas existentes.

Excluir empresas.

### Fornecedores

Listar todos os fornecedores cadastrados.

Criar novos fornecedores.

Editar informações de fornecedores existentes.

Excluir fornecedores.

## 🖥️ Como Rodar o Projeto

Pré-requisitos:

Node.js (versão 16 ou superior) e npm instalados.

O backend deve estar rodando (consulte o README do backend).

Instalar Dependências: No diretório do projeto, instale todas as dependências necessárias:

bash
npm install
Rodar o Projeto: Para iniciar o servidor de desenvolvimento Angular:

bash
ng serve
Acessar o Frontend: Abra o navegador e acesse:

http://localhost:4200

## 📄 Endpoints da API
Configuração de integração com o backend no arquivo environment.ts:
typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
Os serviços consomem os seguintes endpoints do backend:

## Empresas

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
Angular 15 ou superior

HTML5/CSS3 para estilização

TypeScript para tipagem

Bootstrap (opcional) para estilos responsivos

Integração com o backend feito em Spring Boot

## 💡 Boas Práticas Implementadas
Modularização: Componentes organizados por funcionalidade.

Reutilização de Código: Serviços centralizam chamadas à API.

Responsividade: Interface adaptável a diferentes tamanhos de tela.

Typescript Models: Modelos bem definidos para maior tipagem e segurança de dados.

Environment Configuration: Facilita a troca entre ambientes de desenvolvimento, homologação e produção.

## 🔧 Melhorias Futuras
Autenticação: Implementar autenticação e autorização com JWT.

Pesquisa Avançada: Adicionar filtros e ordenação para listagens.

Paginação: Melhorar a performance ao lidar com grandes volumes de dados.

Testes Unitários: Criar testes com Karma e Jasmine.

## 🤝 Contribuições
Sinta-se à vontade para contribuir com melhorias ou sugerir novas funcionalidades. Basta abrir uma issue ou enviar um pull request!