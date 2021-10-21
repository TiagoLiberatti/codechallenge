# codechallenge

## Sobre
Aplicação responsável por gerenciamento de contas bancárias, cadastro, consulta, edição e deleção.
Utilizando angular para o frontend, spring boot para o backend e banco de dados em memória H2.
Arquitetura Clean Arch utilizada, permitindo uma melhor manutenção no código, menor desacoplamento entre classes,
independência do banco de dados, camada de serviço testada separadamente.

## Requisitos
 - docker
 - docker-compose

## Instalações

 #### Windows
    
* [Docker](https://docs.docker.com/desktop/windows/install/)

 #### Linux
* [Docker](https://docs.docker.com/engine/install/ubuntu/)

## Construindo as aplicações local com docker
```
docker-compose up -d
```

## Acessando o frontend
[http://localhost:4200/](http://localhost:4200/)

## Acessando documentação da api com Swagger
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)