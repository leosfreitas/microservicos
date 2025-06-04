
# Plataforma de Microserviços – Documentação Técnica

> **Aluno:** Luigi Orlandi Quinze  
> **Grupo:** Luigi Quinze e Leonardo Freitas  

> Projeto desenvolvido como parte da disciplina de Microserviços, com foco em arquitetura distribuída, integração via gateway, autenticação, observabilidade e entrega contínua.

---

##  Objetivo

Criar uma **plataforma RESTful** com autenticação e integração entre microsserviços utilizando:

- FastAPI (Python) e Spring Boot (Java)
- Autenticação via JWT
- Gateway de entrada centralizado
- Observabilidade com Prometheus e Grafana
- Deploy automatizado via Jenkins e Kubernetes

---

##  Microsserviços

| Serviço   | Linguagem | Porta | Descrição                          |
|-----------|-----------|-------|------------------------------------|
| Gateway   | Java      | 8080  | Entrada única da plataforma        |
| Auth      | Java      | 8081  | Login, JWT, roles                  |
| Account   | Java      | 8082  | Registro e consulta de usuários    |
| Product   | Java      | 8083  | CRUD de produtos com Redis cache   |
| Order     | Java      | 8084  | Criação e listagem de pedidos      |
| Exchange  | Python    | 8085  | Cotação de moedas via API externa  |

---

##  Segurança

!!! tip "JWT - Token de Acesso"
    Toda requisição deve conter o header:

    ```
    Authorization: Bearer <seu_token>
    ```

    O Gateway é responsável por validar este token e repassar as permissões aos demais serviços.

---

##  Observabilidade

| Ferramenta   | Função                          |
|--------------|---------------------------------|
| Prometheus   | Coleta de métricas              |
| Grafana      | Dashboards de visualização      |
| Redis        | Cache distribuído de produtos   |

---

##  CI/CD & Deploy

???+ info "Pipeline Jenkins"

    - Jenkinsfile para cada serviço
    - Docker Buildx com multiplataforma (arm64, amd64)
    - Deploy via Kubernetes (`kubectl`)
    - Secrets & ConfigMaps com arquivos YAML

---

##  Documentação por Serviço

- [`Product API`](product.md)
- [`Order API`](order.md)
- [`Exchange API`](exchange.md)
- [`Bottlenecks`](bottlenecks.md)
- [`Jenkins & Pipeline`](jenkins.md)

---

##  Apresentação

!!! success "Links Importantes"
    - [Vídeo de apresentação do Projeto (2-3 minutos)](https://www.youtube.com/watch?v=Zj4Q0OFiJvA&feature=youtu.be)
    - [Vídeo de apresentação Individual (2-3 minutos)](https://www.youtube.com/watch?v=jRFk8OAOees&feature=youtu.be)


---

##  Repositórios

!!! note "GitHub"
    - [Repositório principal](https://github.com/leosfreitas/microservicos)
    - Serviços:
        - [microservicos-account](https://github.com/leosfreitas/microservicos-account)
        - [microservicos-account-service](https://github.com/leosfreitas/microservicos-account-service)
        - [microservicos-auth](https://github.com/leosfreitas/microservicos-auth)
        - [microservicos-auth-service](https://github.com/leosfreitas/microservicos-auth-service)
        - [microservicos-gateway-service](https://github.com/leosfreitas/microservicos-gateway-service)
        - [microservicos-order](https://github.com/leosfreitas/microservicos-order)
        - [microservicos-order-service](https://github.com/leosfreitas/microservicos-order-service)
        - [microservicos-product](https://github.com/leosfreitas/microservicos-product)
        - [microservicos-product-service](https://github.com/leosfreitas/microservicos-product-service)

---

> _Última atualização: 2025-06-03_  
> Projeto entregue via Blackboard 
