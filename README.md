# Desafio Backend Sênior Conductor

## Stack utilizada no desenvolvimento

- [x] Java 11
- [x] Spring Boot 2.5.4
- [x] Spring Data JPA
- [x] Lombok
- [x] Banco H2
- [x] OpenAPI

# Backend

## Funcionalidades

- [x] Tratamento de conflito de terminais(Ao salvar ou atualizar um terminal, a api valida se já existe outro terminal com o mesmo número logic)
- [x] Atualização completa e parcial do terminal
- [x] Listagem de todos os terminais cadastrados
- [x] Busca de terminal por logic

## Mapeamento da API

Ambiente local: http://localhost:8080

Ambiente remoto: https://conductorchallenge.herokuapp.com

Collection para uso via postman disponível no diretorio collection

- Documentação - /swagger-ui/index.html
- POST - /api/employee
- GET - /api/v1/terminal
- GET - /api/v1/terminal/{logic}
- PUT - /api/v1/terminal/{logic}
- PATCH  - /api/v1/terminal/{logic}