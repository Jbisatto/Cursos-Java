# Controle de Finanças

Projeto para Controle de Finanças usando o conceito uma API REST criada usando Spring Boot e Spring Data JPAcom banco de dados Mysql. Esse projeto faz parte e um desafio da Pub  Future onde o que priorizado nesse projeto foi o back-end.

## Requirementos

1. Maven

2. Java

3. Banco de dados Mysql

4. Postman 


## Configuração

**1. Clonar a aplicação**

```bash
git clone https://github.com
```
**2. Executar o projeto no Maven**
```bash
mvn package
java -jar target/easy-notes-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>
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

**3. Configuração de login do Mysql:**

+ abrir `src/main/resources/application.properties`

+ altere `spring.datasource.username` e `spring.datasource.password`  de acordo com a instalação do seu Mysql


## [Funcionabilidade](https://github.com/Jbisatto/Cursos-Java/wiki/)

