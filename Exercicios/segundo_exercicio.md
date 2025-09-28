# Exercício 2

## Descrição

Você deverá adicionar **autenticação** à aplicação de gerenciamento de produtos criada no Exercício 1.  
A autenticação deve ser feita utilizando o **Quarkus OIDC (`quarkus-oidc`)**, permitindo que apenas usuários autenticados possam acessar os endpoints da API.

---

## Requisitos Funcionais

### 1. Configuração do OIDC
- Configure o Quarkus para usar `quarkus-oidc` com um **provedor OIDC** (exemplo: Keycloak).

### 2. Proteção de Endpoints
- Os endpoints da API `/produtos` devem exigir autenticação.

### 3. Controle de Acesso por Papel (Roles)
- Defina pelo menos dois papéis:
    - **admin**: pode criar, atualizar e excluir produtos.
    - **user**: pode apenas listar e buscar produtos.
- A aplicação deve validar os papéis contidos no token JWT e permitir ou negar acesso de acordo.

### 4. Exemplo de Regras de Acesso
- **POST /produtos** → somente `admin`.
- **PUT /produtos/{id}** → somente `admin`.
- **DELETE /produtos/{id}** → somente `admin`.
- **GET /produtos** e **GET /produtos/{id}** → `admin` e `user`.

---

## Critérios de Avaliação (Exercício 2)

- Configuração correta do **quarkus-oidc**.
- Proteção dos endpoints usando **anotações de segurança** (`@RolesAllowed`, `@Authenticated`).
- Implementação correta das regras de acesso por papel.
- Estrutura organizada do código e configuração.
