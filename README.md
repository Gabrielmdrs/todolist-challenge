<h1 align="center">
  TODO List
</h1>



API para gerenciar tarefas (CRUD) que faz parte desse desafio para pessoas desenvolvedoras backend júnior, que se candidataram para a [Simplify](https://github.com/simplify-liferay/desafio-junior-backend-simplify).


## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Mysql](https://dev.mysql.com/downloads/)
- [Docker Compose](https://docs.docker.com/compose/)

## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- Execução do MySQL em conteiner docker

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Configurar arquivo docker compose
- Executar comando docker:
```
$ cd docker
$docker-compose up -d
```
- Executar a aplicação:
```
$ java -jar target/todolist-challenge-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [httpie](https://httpie.io):

- Criar Tarefa
```
$ http POST :8080/todos name="Todo 1" description="Desc Todo 1" priority=1

[
  {
    "description": "Desc Todo 1",
    "id": 1,
    "name": "Todo 1",
    "priority": 1,
    "done": false
  }
]
```

- Listar Tarefas
```
$ http GET :8080/todos

[
  {
    "description": "Desc Todo 1",
    "id": 1,
    "name": "Todo 1",
    "priority": 1,
    "done": false
  }
]
```

- Atualizar Tarefa
```
$ http PUT :8080/todos/1 name="Todo 1 Up" description="Desc Todo 1 Up" priority=2

[
  {
    "description": "Desc Todo 1 Up",
    "id": 1,
    "name": "Todo 1 Up",
    "priority": 2,
    "done": false
  }
]
```

- Remover Tarefa
```
http DELETE :8080/todos/1

[ ]