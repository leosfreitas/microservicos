
# Bottlenecks – Observabilidade e Cache

> Este módulo da plataforma trata da **mitigação de gargalos (bottlenecks)** através de soluções de monitoramento e cache:
> - Monitoramento com **Prometheus + Grafana**
> -  Cache com **Redis**

---

## Grafana

Painel de visualização gráfica dos dados coletados pelo Prometheus.

### Kubernetes: `grafana/k8s/k8s.yaml`

- Cria:
  - `Deployment`: com imagem `grafana/grafana-enterprise`
  - `Service`: expõe a porta `3000`
- Admin password: `admin`
- Armazena os dashboards em volume `emptyDir`

 *Print do dashboard do Grafana aqui*  
![grafana-dashboard](./imgs/grafana.png)

---

## Prometheus

Responsável por coletar métricas dos microsserviços (via `/actuator/prometheus`).

### Kubernetes: `prometheus/k8s/k8s.yaml`

- Cria:
  - `ConfigMap` com `prometheus.yml`:
    ```yaml
    scrape_configs:
      - job_name: 'prometheus'
        static_configs:
          - targets: ['localhost:9090']
    ```
  - `Deployment`: com imagem `prom/prometheus:latest`
  - `Service`: expõe a porta `9090`

 *Print do Prometheus aqui*  
![prometheus-ui](./imgs/prometheus.png)

---

## ⚡ Redis – Cache In-Memory

Utilizado principalmente no `product-service` para:
- Caching da lista de produtos (`GET /product`)
- Redução da latência (80ms → 5ms)
- Desacoplamento da base de dados

### Kubernetes: `redis/k8s/k8s.yaml`

- Cria:
  - `Deployment`: com autenticação via `redis-secret`
  - `Volumes`:
    - `redis-config`: montado em `/etc/redis`
    - `redis-data`: persistente via `redis-pvc`
  - `Readiness` e `Liveness` probes com `redis-cli ping`
- Imagem: `redis:7-alpine`

 *Print mostrando Redis em execução aqui*  
![redis-status](./imgs/redis.png)

---

## 🔍 Métricas observadas

| Serviço        | Métrica                          | Descrição                         |
|----------------|----------------------------------|-----------------------------------|
| Todos          | `http_server_requests_seconds_*` | Latência e contagem de requests   |
| `product`      | `cache.gets`, `cache.puts`       | Hits/misses de cache              |
| `order`        | `order_total_sum` (custom)       | Soma total de pedidos gerados     |

---

## Conclusão

- A stack **Prometheus + Grafana + Redis** melhora tanto a **observabilidade** quanto a **performance**.
- Ajuda a antecipar problemas (ex: memória, CPU, falhas de endpoint).
- Reduz carga de banco de dados e melhora tempo de resposta.

> Recomenda-se manter esse setup ativo mesmo em produção local (Minikube ou Docker Desktop).

---

> _Última revisão: 2025-06-03_
