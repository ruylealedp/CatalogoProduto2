# Exercício 1

## Descrição

Você deverá criar uma aplicação que disponibilize uma API REST para gerenciar produtos.  
A API deve permitir realizar as operações de **CRUD (Create, Read, Update, Delete)** de produtos.

---

## Requisitos Funcionais

### 1. Criar Produto
- **Método HTTP:** `POST`
- **Path:** `/produtos`
- **Corpo da requisição:** JSON com os dados do produto.
- **Status esperado:**
    - `201 Created` em caso de sucesso.
    - `400 Bad Request` em caso de erro de validação.

### 2. Listar Produtos
- **Método HTTP:** `GET`
- **Path:** `/produtos`
- **Status esperado:**
    - `200 OK` com a lista de produtos.

### 3. Buscar Produto por ID
- **Método HTTP:** `GET`
- **Path:** `/produtos/{id}`
- **Status esperado:**
    - `200 OK` em caso de sucesso.
    - `404 Not Found` se o produto não existir.

### 4. Atualizar Produto
- **Método HTTP:** `PUT`
- **Path:** `/produtos/{id}`
- **Corpo da requisição:** JSON com os dados atualizados do produto.
- **Status esperado:**
    - `200 OK` em caso de sucesso.
    - `400 Bad Request` em caso de erro de validação.
    - `404 Not Found` se o produto não existir.

### 5. Excluir Produto
- **Método HTTP:** `DELETE`
- **Path:** `/produtos/{id}`
- **Status esperado:**
    - `204 No Content` em caso de sucesso.
    - `404 Not Found` se o produto não existir.

---

## Modelo de Produto
Cada produto deve possuir no mínimo os seguintes atributos:
- `id` (gerado automaticamente)
- `nome` (obrigatório)
- `descricao` (opcional)
- `preco` (obrigatório, maior que zero)

---

## Critérios de Avaliação (Exercício 1)

- Uso correto dos **métodos HTTP** (`GET`, `POST`, `PUT`, `DELETE`).
- Uso correto dos **status HTTP**.
- Uso correto e consistente dos **paths**.
- Validações básicas de entrada.
- Estrutura organizada do código.  
