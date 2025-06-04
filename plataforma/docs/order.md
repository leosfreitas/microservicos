
#  Order API – Pedidos do Usuário

> Microsserviço responsável pela criação e consulta de **pedidos**, com base em produtos cadastrados.  
> Todas as requisições devem conter o header `id-account` identificando o cliente.

---

##  POST `/order` – Criar Pedido

Cria um novo pedido para o usuário autenticado.

??? example "Requisição"
    ```http
    POST /order
    id-account: 0195ae95-5be7-7dd3-b35d-7a7d87c404fb
    Content-Type: application/json

    {
      "items": [
        { "idProduct": "0195abfb-7074-73a9-9d26-b4b9fbaab0a8", "quantity": 2 },
        { "idProduct": "0195abfe-e416-7052-be3b-27cdaf12a984", "quantity": 1 }
      ]
    }
    ```

??? success "Resposta `200 OK`"
    ```json
    {
      "id": "0195ac33-73e5-7cb3-90ca-7b5e7e549569",
      "date": "2025-09-01T12:30:00",
      "items": [...],
      "total": 26.44
    }
    ```

!!! tip "Print"
    ![print post /order](./imgs/post_order.png)

---

##  GET `/order` – Listar Pedidos do Usuário

Retorna um resumo de todos os pedidos do `id-account`.

??? example "Requisição"
    ```http
    GET /order
    id-account: 0195ae95-5be7-7dd3-b35d-7a7d87c404fb
    ```

??? success "Resposta `200 OK`"
    ```json
    [
      {
        "id": "0195ac33-73e5-7cb3-90ca-7b5e7e549569",
        "date": "2025-09-01T12:30:00",
        "total": 26.44
      },
      {
        "id": "0195ac33-cbbd-7a6e-a15b-b85402cf143f",
        "date": "2025-10-09T03:21:57",
        "total": 18.6
      }
    ]
    ```

!!! tip "Print"
    ![print get /order](./imgs/get_orders.png)

---

##  GET `/order/{id}` – Buscar Detalhes do Pedido

Retorna os detalhes de um pedido específico do usuário.

??? example "Requisição"
    ```http
    GET /order/0195ac33-73e5-7cb3-90ca-7b5e7e549569
    id-account: 0195ae95-5be7-7dd3-b35d-7a7d87c404fb
    ```

??? success "Resposta `200 OK`"
    ```json
    {
      "id": "0195ac33-73e5-7cb3-90ca-7b5e7e549569",
      "date": "2025-09-01T12:30:00",
      "items": [
        {
          "id": "01961b9a-bca2-78c4-9be1-7092b261f217",
          "product": { "id": "0195abfb-7074-73a9-9d26-b4b9fbaab0a8" },
          "quantity": 2,
          "total": 20.24
        },
        {
          "id": "01961b9b-08fd-76a5-8508-cdb6cd5c27ab",
          "product": { "id": "0195abfe-e416-7052-be3b-27cdaf12a984" },
          "quantity": 10,
          "total": 6.2
        }
      ],
      "total": 26.44
    }
    ```

!!! warning "Possíveis Erros"
    | Código | Motivo                                  |
    |--------|------------------------------------------|
    | 404    | Pedido não pertence ao usuário ou inexistente |

!!! tip "Print"
    ![print get /order/{id}](./imgs/get_order_by_id.png)

---

## Autenticação e Cabeçalhos

!!! info "Requisito de Autenticação"
    Todos os endpoints exigem o cabeçalho:
    ```http
    id-account: <UUID do usuário autenticado>
    ```

    - Extraído do token JWT pela camada de autenticação no gateway.

---

##  Observabilidade

!!! note "Métricas Ativadas"
    - `http_server_requests_seconds_count{uri="/order"}`
    - `order_total_sum`
    - Integração com Prometheus e Grafana habilitada.

---

##  Regras e Validações

!!! important "Validações do Serviço"
    - `idProduct` precisa existir no catálogo.
    - `quantity >= 1`.
    - `total` é calculado dinamicamente.

---

##  Execução Local

??? example "Comando"
    ```bash
    ./mvnw spring-boot:run
    ```

- Interface Swagger disponível em:
  ```
  http://localhost:8080/swagger-ui.html
  ```

---

> _Última atualização da interface: `OrderController.java`_
