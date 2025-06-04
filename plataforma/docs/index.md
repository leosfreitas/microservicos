
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
    - [Vídeo de apresentação (2-3 minutos)](https://www.youtube.com/seu_video)
    - [Slides do projeto](https://docs.google.com/presentation/d/seu_slide_id)

---

##  Repositórios

!!! note "GitHub"
    - [Repositório principal](https://github.com/seu_usuario/seu_repo)
    - Serviços:
        - [product-service](https://github.com/seu_usuario/product-service)
        - [exchange-service](https://github.com/seu_usuario/exchange-service)

---

> _Última atualização: 2025-06-03_  
> Projeto entregue via Blackboard 
