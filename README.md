# ✅ Tasks API — Spring Boot + JWT

## 📌 Descrição em Português

Este projeto é uma API REST desenvolvida com **Spring Boot**, que permite o gerenciamento de tarefas com autenticação baseada em **JWT (JSON Web Token)**.

### 🔐 Funcionalidades

- Registro de usuários
- Login com geração de JWT
- Autenticação e autorização com tokens
- Criação, leitura, atualização e remoção de tarefas
- Endpoint para renovação de token (refresh token)
- Documentação interativa com Swagger
- Proteção de rotas com Spring Security

### 🧱 Tecnologias

- Java 17+
- Spring Boot
- Spring Security
- JWT
- H2 Database (desenvolvimento)
- Swagger / OpenAPI

### 🔑 Endpoints principais

- `POST /auth/register` — Registrar novo usuário
- `POST /auth/login` — Autenticação e geração de token
- `POST /auth/refresh-token` — Renovação do token
- `GET /tasks` — Listar tarefas (protegido)
- `POST /tasks` — Criar tarefa (protegido)
- `GET /tasks/id` — Buscar tarefa por ID (protected)
- `PUT /tasks/id` — Atualizar tarefa por ID (protected)
- `DELETE /tasks/id` — Deletar tarefa por ID (protected)

---

## 🌍 English Description

This is a RESTful API built with **Spring Boot** for managing tasks with **JWT-based authentication**.

### 🔐 Features

- User registration
- Login with JWT generation
- Stateless authentication and route protection
- Task CRUD operations
- Token refresh endpoint
- Swagger documentation
- Spring Security integration

### 🧱 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT
- H2 Database (for development)
- Swagger / OpenAPI

### 🔑 Main Endpoints

- `POST /auth/register` — Register a new user
- `POST /auth/login` — Login and get token
- `POST /auth/refresh` — Refresh your JWT
- `GET /tasks` — List tasks (protected)
- `POST /tasks` — Create a task (protected)
- `GET /tasks/id` — List task by ID (protected)
- `PUT /tasks/id` — Update task by ID (protected)
- `DELETE /tasks/id` — Delete task by ID (protected)

---

### 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

### 🙋‍♂ Autor

Feito com 💻 por [Breno Nunes](https://github.com/brenonun3s)
