# DemoDAOJDBC

Aplicação Java que demonstra o uso do padrão DAO (Data Access Object) para acessar e manipular dados em um banco de dados relacional. A aplicação foi desenvolvida utilizando JDBC (Java Database Connectivity) para realizar operações de CRUD (Create, Read, Update, Delete) em entidades como **Department** (Departamento) e **Seller** (Vendedor).

A arquitetura do projeto é organizada de forma a separar as camadas de negócio e de persistência de dados, tornando o código mais modular e de fácil manutenção. O projeto inclui uma camada de abstração com interfaces genéricas e implementações específicas para as entidades, além de utilizar padrões como Factory para gerenciar a criação dos objetos DAO.

## Funcionalidades
- CRUD para entidades Department (Departamento) e Seller (Vendedor)
- Consultas com relacionamento entre entidades (vendedores associados a departamentos)
- Uso do JDBC para conexão e execução de queries no banco de dados
- Tratamento de exceções relacionadas a operações com o banco de dados
- Interface genérica para operações de DAO
- Utilização de **PreparedStatements** para segurança e desempenho

## Estrutura do Projeto
- `model.entities:` Contém as classes de entidade, como Department e Seller, que representam as tabelas do banco de dados.
- `model.dao:` Contém as interfaces DAO, que definem as operações a serem implementadas.
- `model.dao.impl:` Contém as implementações das interfaces DAO, que realizam as operações reais no banco de dados utilizando JDBC.
- `model.dao.util:` Contém enums com os campos das tabelas de banco de dados para facilitar a manutenção e evitar uso de strings fixas nas queries.
- `db:` Pacote responsável por gerenciar a conexão com o banco de dados e o tratamento de exceções relacionadas.

## Requisitos
- Java 8 ou superior
- JDBC Driver compatível com o banco de dados utilizado (ex.: MySQL)
- Banco de dados configurado com tabelas para Department e Seller
  
# Executando o Projeto

1. Clone o repositório:
   
```bash
git clone https://github.com/seu-usuario/DemoDAOJdbc.git
```

2. Configure o banco de dados (ex.: MySQL) com as tabelas Department e Seller:

```sql
CREATE TABLE department (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100)
);

CREATE TABLE seller (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Email VARCHAR(100),
    BirthDate DATE,
    BaseSalary DOUBLE,
    DepartmentId INT,
    FOREIGN KEY (DepartmentId) REFERENCES department(Id)
);
```

3. Configure o arquivo `db.properties` com as informações do seu banco de dados:

```properties
dburl=jdbc:mysql://localhost:3306/sua-base-de-dados
user=seu-usuario
password=sua-senha
```
4. Compile e execute o projeto a partir das classes de Teste


