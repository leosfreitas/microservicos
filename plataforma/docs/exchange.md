
# Exchange API – Conversão de Moedas

> Este microsserviço expõe uma rota pública para conversão de moedas, utilizando uma API de terceiros (como **AwesomeAPI** ou **ExchangeRate-API**) e aplicando uma taxa de spread configurável.

---

## Método Único: `GET /exchange/{from_currency}/{to_currency}`

Converte o valor de uma moeda de origem (`from_currency`) para uma moeda de destino (`to_currency`), aplicando um spread percentual.

### 🔸 Exemplo de chamada

```http
GET /exchange/USD/EUR
Authorization: Bearer <token>
```

### 🔹 Exemplo de resposta `200 OK`

```json
{
  "sell": 0.82,
  "buy": 0.80,
  "date": "2025-09-01T14:23:42",
  "account_id": "0195ae95-5be7-7dd3-b35d-7a7d87c404fb"
}
```

 *Print da chamada funcionando aqui*  
![print get /exchange](./imgs/get_exchange.png)

---

## Regras de Negócio

- O usuário **deve estar autenticado** (JWT válido).
- A resposta exibe:
  - `sell`: cotação de venda com **spread aplicado**
  - `buy`: cotação de compra com **spread aplicado**
  - `date`: timestamp da requisição
  - `account_id`: extraído do `jti` presente no JWT

### Spread

O spread é definido pela variável de ambiente:

```env
SPREAD_PERCENTAGE=2.0  # padrão: 2%
```

Fórmulas:

```
buy  = base_rate * (1 - spread/2)
sell = base_rate * (1 + spread/2)
```

---

## Autenticação

JWT baseado em `Authorization: Bearer <token>`  
- A chave secreta é decodificada de Base64 (`JWT_SECRET_KEY`)
- Algoritmo padrão: `HS256`

---

## Dependências Externas

- `.env` com:
  ```env
  EXCHANGE_API_BASE_URL=https://api.exchangerate-api.com/v4
  EXCHANGE_API_KEY=SUA_CHAVE
  JWT_SECRET_KEY=<base64>
  JWT_ALGORITHM=HS256
  SPREAD_PERCENTAGE=2.0
  ```

- Bibliotecas:
  - `fastapi`, `requests`, `python-jose`, `pydantic`, `python-dotenv`

---

## Erros comuns

| Código | Causa |
|--------|-------|
| `401` | Token inválido ou ausente |
| `404` | Moeda de destino não encontrada |
| `502` | Falha na chamada à API externa |

---

## Como rodar local

```bash
# Instalar dependências
pip install -r requirements.txt

# Rodar API
uvicorn app:app --reload --port 8080
```

A documentação Swagger estará disponível em:  
`http://localhost:8080/docs`

---

> _Última atualização da rota: `GET /exchange/{from}/{to}`_
