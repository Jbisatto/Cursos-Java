# Controle de Finanças

Projeto para Controle de Finanças usando o conceito uma API REST criada usando Spring Boot e Spring Data JPA com banco de dados Mysql. Esse projeto faz parte e um desafio da Pub  Future onde o que priorizado nesse projeto foi o back-end.

## Requirementos

1. Maven

2. Java

3. Banco de dados Mysql

4. Postman 


## Configuração

**1. Clonar a aplicação**

```bash
git clone https://github.com/Jbisatto/desafiopubfuture.git
```
**2. Executar o projeto no Maven**
```bash
mvn package
java -jar target/easy-notes-1.0.0.jar
```

Outra alternativa:

```bash
mvn spring-boot:run
```

## Banco de dados
**1. Criar no Mysql um banco de dados com o nome _desafioPubFuture_**

```bash
create database desafiopubfuture;
```

**2. Utilize o script MySQL contido no arquivo _data.sql_ para criar e popular o banco de dados**
  `src/main/resources/data.sql
`
**3. Configuração de login do Mysql:**

+ abrir `src/main/resources/application.properties`

+ altere `spring.datasource.username` e `spring.datasource.password`  de acordo com a instalação do seu Mysql


## Funcionabilidade
Como o projeto foi feita apenas o back-end, para testar suas funcionabilidade foi usado o Postman. O projeto apresenta as seguintes funções:


### [Receitas](https://github.com/Jbisatto/Cursos-Java/wiki/Receitas)

*  Cadastrar receitas

*  Editar receitas

*  Remover receitas

*  Listar receitas
  >*  Filtro por período (dataInicial – dataFinal)
  >*  Filtro por tipo de receita

*  Listar total de receitas

### [Despesas](https://github.com/Jbisatto/Cursos-Java/wiki/Despesas)

* Cadastrar despesas

*  Editar despesas

*  Remover despesas

*  Listar despesas
  >*  Filtro por período (dataInicial – dataFinal)
 >*  Filtro por tipo de despesa

*  Listar total de despesas

### [Contas](https://github.com/Jbisatto/Cursos-Java/wiki/Conta)
*  Cadastrar conta
*  Editar conta
*  Remover conta
*  Listar contas
*  Transferir saldo entre contas
*  Listar saldo total

