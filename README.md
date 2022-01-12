# Controle de Finanças

Projeto para Controle de Finanças usando o conceito uma API REST criada usando Spring Boot e Spring Data JPAcom banco de dados Mysql. Esse projeto faz parte e um desafio da Pub  Future onde o que priorizado nesse projeto foi o back-end.

## Requirementos

1. Java

2. Banco de dados Mysql

3. Postman

4. IDE Eclipse 


## Configuração

**1. Clonar a aplicação**

```bash
git clone https://github.com
```
## Banco de dados
**1. Criar um database Mysql database**

```bash
create database desafioPubFuture;
```

Utilize o script MySQL contido no arquivo **data.sql** para criar e popular o Banco de Dados.

**2. Criar um database Mysql database**

```bash
create database desafioPubFuture;
```

**3. Change mysql username and password as per your installation**

+ abrir `src/main/resources/application.properties`

+ altere `spring.datasource.username` e `spring.datasource.password`  de acordo com a instalação do seu Mysql



## Project setup
```
mvn install
```
Documentação(https://github.com/Jbisatto/Cursos-Java/wiki)
### Em desenvolvimeto...
# Projeto back-end com Spring Boot para Controle de Pedidos e Delivery

- Spring Framework
- Spring Boot
- Spring Data
- REST
- Swagger
- Mysql
- JdbcTemplate

Para testes unitários:
- JUnit
- Mockito

O projeto consiste em uma API para controle de pedidos, desenvolvido totalmente com a tecnologia Java,
utilizando as tecnologias acima mencionadas.

O projeto possui cadastro de:
##### - Mercadoria (Products)
##### - Cliente (Customers)

Para a parte de movimentação da API temos
##### - Pedido (Order)
##### - Caixa (CashRegister)

## Configurando o projeto

1) git clone ou download do zip: https://github.com/GabrielGRS0206/portifolio-backend-spring-boot

2) Importe o projeto em sua IDE de preferência

3) Altere o usuario e senha para que o projeto possa acessar o banco mysql. 
  * Vá até `/src/main/resources/application.properties`;
  * Altere as propriedades informado o usuário e senha do seu banco de dados: 
    - spring.datasource.username=usuario
    - spring.datasource.password=senha
    - spring.datasource.url = jdbc:mysql://localhost:3306/seuBancoDeDados?useSSL=false
4) Na classe App de um run na sua IDE.
5) Acesse: http://localhost:8090/api/app-order/swagger-ui.html para visualizar os endpoints
