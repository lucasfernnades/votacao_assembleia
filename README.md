# **votacao_assembleia**

API REST para votação de pautas em assembleia

## Stack utilizada

- Java 11
- Spring
- Maven 3.9.6
- MongoDB
- RabbitMQ
- Redis
- Docker

O projeto se encontra hospedado em uma instância do EC2, sendo possivel as requisições atraves da url **ec2-54-237-115-229.compute-1.amazonaws.com:8080/...**

Duvidas sobre endpoints e conteúdo das requisições acesse:
- **ec2-54-237-115-229.compute-1.amazonaws.com:8080/swagger-ui.html**

## Tarefa Bônus 1 - Integração com sistemas externos
Devido ao **https://user-info.herokuapp.com/users/{cpf}** estar fora de funcionamento durante o desenvolvimento do projeto, o mesmo foi substituido pela integração com a api de validação de CPF disponivel atraves do site **https://api.invertexto.com/**.

## Tarefa Bônus 2 - Mensageria e filas
Como mensageria e fila foi escolhido o RabbitMq devido a ser um projeto pequeno e mais simples.

## Tarefa Bônus 3 - Performance
Para performance foi utilizado a compresão de dados para a resposta da requisição, além da implementação do Redis como cache para otimizar e reduzir os acessos necessarios ao banco de dados.

## Tarefa Bônus 4 - Versionamento da API
Foi escolhido o método de versionamento da API pela url, devido a facilidade de implementação e falta de experiência sobre outros potenciais métodos.
