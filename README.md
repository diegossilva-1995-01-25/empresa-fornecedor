# Empresa-Fornecedor

Protótipo de CRUD de para as entidades Empresa e Fornecedor, bem como de suas relações... Tudo isso dentro de seu perfil de Usuário.

Entidades:
1. Usuário;
2. Empresa;
3. Fornecedor;
4. Empresa-Fornecedor (entidade criada pra resolver o problema de relação muitos-para-muitos, many-per-many).

## Propósito Original
Servir como teste em processo seletivo para ingresso em uma empresa, a saber, a A**e****e.

## Tecnologias Utilizadas

### Back-End
1. Java v21;
2. SpringBoot v4.0.6;
	- Spring Web;
	- Spring Security;
	- Spring JPA entre outros.

### Banco de Dados
- PostgreSQL v12

### Front-End
- Angular v21.2

### Nota
Script de Banco de Dados por trás da criação do banco e suas tabelas incluso. Bem como uma cópia do ambiente de testes de API Postman.

### RoadMap
O que está pendente?
- Front-End:
	- Parte de tela para inclusão de fornecedores à empresa selecionada;
	- O mesmo vale para incluir empresas ao fornecedor selecionado;
	- Tela para consulta de lista de empresas por nome;
	- Tela para consulta de lista de fornecedores por nome;
- Back-End:
	- Classes de teste JUnit para as services.