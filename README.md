# Video
Aplicação web de streaming de vídeos utilizando as tecnologias Spring Framework, Spring Web Flux, Spring Boot e Spring Data. A aplicação deve permitir o gerenciamento e a exibição de vídeos, onde cada vídeo possui um título, descrição, URL e data de publicação 
# Documentação de Arquitetura - Video Streaming App

O Video Streaming App é uma aplicação web reativa projetada para fornecer uma plataforma eficiente e escalável de streaming de vídeos.

## Estrutura do Projeto

A aplicação segue uma arquitetura baseada em microserviços, organizada da seguinte forma:

### Pacotes

- `controller`: Controladores RESTful que gerenciam as operações HTTP para categorias, vídeos, usuários e favoritos.
- `exception`: Classes de exceção personalizadas para o tratamento de erros da aplicação.
- `model`: Entidades de domínio, incluindo `Category`, `User`, `UserFavorite`, `Video` e `VideoStatistics`.
- `repository`: Interfaces do Spring Data para a camada de persistência, permitindo interações não bloqueantes com o banco de dados.
- `service`: Serviços que contêm a lógica de negócios e interagem com os repositórios.

### Recursos

- `application.properties`: Configurações da aplicação, incluindo conexão com o banco de dados H2 em memória.
- `schema.sql`: Definições de esquema para criação de tabelas no banco de dados.

## Configuração da Aplicação

Utiliza o banco de dados em memória H2 configurado para operar de forma não bloqueante, inicializado durante a inicialização da aplicação.

## Tecnologia

- **Spring Boot**: Simplifica a configuração e o lançamento de serviços.
- **Spring WebFlux**: Oferece suporte a APIs reativas e não bloqueantes.
- **H2 Database**: Banco de dados em memória utilizado para desenvolvimento e testes.
- **R2DBC**: Fornece uma API reativa para conectividade com bancos de dados relacionais.

## Considerações Finais

A arquitetura foi selecionada para atender às necessidades de uma plataforma de streaming de vídeo, suportando alto volume de requisições e acesso rápido a dados. A escolha por um banco de dados em memória reflete o foco no desenvolvimento ágil e na execução de testes automatizados. O projeto segue as melhores práticas do setor, incluindo separação de camadas, uso de DTOs, tratamento de exceções e testes abrangentes, indicativos de uma base de código bem estruturada e mantível

# Documentação da API de Vídeos

## Endpoints

### Obter Todos os Vídeos

GET /api/videos

**Exemplo de Resposta (200 OK)**
```json
[
    {
        "id": 1,
        "title": "Vídeo 1",
        "description": "Descrição do Vídeo 1",
        "url": "https://www.example.com/video1",
        "publicationDate": "2022-01-28T10:00:00Z",
        "favorite": true,
        "categoryId": 1
    },
    {
        "id": 2,
        "title": "Vídeo 2",
        "description": "Descrição do Vídeo 2",
        "url": "https://www.example.com/video2",
        "publicationDate": "2022-01-28T11:00:00Z",
        "favorite": false,
        "categoryId": 2
    }
]
```
### Obter Vídeo por ID
Retorna um vídeo específico pelo seu ID.

Parâmetros de URL
{id} (Long): O ID do vídeo que deseja obter.

**Exemplo de Resposta (200 OK)**
GET /api/videos/{id}

```json
{
    "id": 1,
    "title": "Vídeo 1",
    "description": "Descrição do Vídeo 1",
    "url": "https://www.example.com/video1",
    "publicationDate": "2022-01-28T10:00:00Z",
    "favorite": true,
    "categoryId": 1
}
```
### Marcar/Desmarcar Vídeo como Favorito
Altera o status de favorito de um vídeo específico.

Parâmetros de URL
{videoId} (Long): O ID do vídeo que deseja marcar/desmarcar como favorito.

**Exemplo de Requisição:
PATCH /api/videos/{videoId}

**Exemplo de Resposta (200 OK)**

```json
{
    "message": "Vídeo marcado como favorito com sucesso."
}
```

### Listar Vídeos por Categoria
GET /api/videos/category/{categoryId}

Retorna uma lista de vídeos pertencentes a uma categoria específica.

Parâmetros de URL
{categoryId} (Long): O ID da categoria pela qual deseja filtrar os vídeos.

**Exemplo de Requisição:**
GET /api/videos/category/1

```json
[
    {
        "id": 1,
        "title": "Vídeo 1",
        "description": "Descrição do Vídeo 1",
        "url": "https://www.example.com/video1",
        "publicationDate": "2022-01-28T10:00:00Z",
        "favorite": true,
        "categoryId": 1
    },
    {
        "id": 3,
        "title": "Vídeo 3",
        "description": "Descrição do Vídeo 3",
        "url": "https://www.example.com/video3",
        "publicationDate": "2022-01-28T12:00:00Z",
        "favorite": false,
        "categoryId": 1
    }
]
```

### Listar Vídeos Favoritos
GET /api/videos/favorites

Retorna uma lista de todos os vídeos marcados como favoritos.

Exemplo de Requisição
GET /api/videos/favorites

**Exemplo de Resposta (200 OK):**

```json
[
    {
        "id": 1,
        "title": "Vídeo 1",
        "description": "Descrição do Vídeo 1",
        "url": "https://www.example.com/video1",
        "publicationDate": "2022-01-28T10:00:00Z",
        "favorite": true,
        "categoryId": 1
    },
    {
        "id": 4,
        "title": "Vídeo 4",
        "description": "Descrição do Vídeo 4",
        "url": "https://www.example.com/video4",
        "publicationDate": "2022-01-28T13:00:00Z",
        "favorite": true,
        "categoryId": 2
    }
]
```
## Criar Novo Vídeo
POST /api/videos

Corpo da Requisição:

```json
{
    "title": "Novo Vídeo",
    "description": "Descrição do Novo Vídeo",
    "url": "https://www.example.com/novovideo",
    "publicationDate": "2022-01-28T14:00:00Z",
    "favorite": false,
    "categoryId": 1
}
```
**Exemplo de Requisição**
POST /api/videos

**Exemplo de Resposta (201 Created)

```json
{
    "id": 5,
    "title": "Novo Vídeo",
    "description": "Descrição do Novo Vídeo",
    "url": "https://www.example.com/novovideo",
    "publicationDate": "2022-01-28T14:00:00Z",
    "favorite": false,
    "categoryId": 1
}
```
### Excluir Vídeo
DELETE /api/videos/{id}
Exclui um vídeo específico.
Parâmetros de URL
{id} (Long): O ID do vídeo que deseja excluir.
**Exemplo de Requisição
DELETE /api/videos/1
Exemplo de Resposta (204 No Content)
Não há corpo de resposta.

### Obter Estatísticas dos Vídeos
GET /api/videos/statistics

Retorna estatísticas dos vídeos, incluindo o número total de favoritos e a média de visualizações.

**Exemplo de Requisição:
GET /api/videos/statistics

**Exemplo de Resposta (200 OK)**

```json
{
    "totalFavorites": 3,
    "averageViews": 1000
}
```
## FavoriteController

### Adicionar Vídeo aos Favoritos

**Método:** POST `/favorites`

Este endpoint permite adicionar um vídeo aos favoritos de um usuário.

**Parâmetros da Requisição:**
- `UserFavorite` (JSON): Um objeto JSON que contém as informações necessárias para adicionar um vídeo aos favoritos. O objeto deve ter as seguintes propriedades:
  - `userId` (Long): O ID do usuário que deseja adicionar o vídeo aos favoritos.
  - `videoId` (Long): O ID do vídeo que deseja adicionar aos favoritos.

**Exemplo de Requisição:**
```json
{
    "userId": 1,
    "videoId": 5
}
```
Resposta de Sucesso (200 OK):
O vídeo foi adicionado aos favoritos com sucesso.

### Obter Vídeos Favoritos de um Usuário
Método: GET /favorites/{userId}

Este endpoint permite obter a lista de vídeos favoritos de um usuário com base em seu ID.

Parâmetros da URL:

{userId} (Long): O ID do usuário para o qual deseja obter a lista de vídeos favoritos.
**Exemplo de Requisição:
GET /favorites/1

Resposta de Sucesso (200 OK):
A resposta contém uma lista de vídeos que são favoritos do usuário com o ID especificado.

Cada vídeo na lista possui as seguintes informações:

id (Long): O ID do vídeo.
title (String): O título do vídeo.
description (String): A descrição do vídeo.
url (String): A URL do vídeo.
publicationDate (String): A data de publicação do vídeo.
favorite (Boolean): Indica se o vídeo é favorito para o usuário.
categoryId (Long): O ID da categoria à qual o vídeo pertence.
**Exemplo de Resposta:**

```json
[
    {
        "id": 1,
        "title": "Vídeo 1",
        "description": "Descrição do Vídeo 1",
        "url": "https://www.example.com/video1",
        "publicationDate": "2022-01-28T10:00:00Z",
        "favorite": true,
        "categoryId": 1
    },
    {
        "id": 4,
        "title": "Vídeo 4",
        "description": "Descrição do Vídeo 4",
        "url": "https://www.example.com/video4",
        "publicationDate": "2022-01-28T13:00:00Z",
        "favorite": true,
        "categoryId": 2
    }
]
```
## UserController

### Registrar Novo Usuário

**Método:** POST `/users/register`

Este endpoint permite registrar um novo usuário.

**Parâmetros da Requisição:**
- `User` (JSON): Um objeto JSON que contém as informações do novo usuário. O objeto deve ter as seguintes propriedades:
  - `username` (String): O nome de usuário do novo usuário.
  - `email` (String): O endereço de e-mail do novo usuário.
  - `password` (String): A senha do novo usuário.

**Exemplo de Requisição:**
```json
{
    "username": "novousuario",
    "email": "novousuario@example.com",
    "password": "senha123"
}
```

Resposta de Sucesso (200 OK):
O novo usuário foi registrado com sucesso. A resposta contém as informações do usuário registrado.

Resposta de Erro (400 Bad Request):
Se houver algum problema ao registrar o novo usuário, uma resposta de erro será retornada.

###Obter Todos os Usuários
Método: GET /users

Este endpoint permite obter a lista de todos os usuários registrados.

**Exemplo de Requisição:**
GET /users

Resposta de Sucesso (200 OK):
A resposta contém uma lista de todos os usuários registrados.

Cada usuário na lista possui as seguintes informações:

id (Long): O ID do usuário.
username (String): O nome de usuário do usuário.
email (String): O endereço de e-mail do usuário.
**Exemplo de Resposta:**

```json
[
    {
        "id": 1,
        "username": "usuario1",
        "email": "usuario1@example.com"
    },
    {
        "id": 2,
        "username": "usuario2",
        "email": "usuario2@example.com"
    }
]
```
## CategoriaController

### Criar Nova Categoria

**Método:** POST `/categories`

Este endpoint permite criar uma nova categoria.

**Parâmetros da Requisição:**
- `Category` (JSON): Um objeto JSON que contém as informações da nova categoria. O objeto deve ter as seguintes propriedades:
  - `name` (String): O nome da nova categoria.

**Exemplo de Requisição:**
```json
POST /categories
{
    "name": "Nova Categoria"
}
```
Resposta de Sucesso (200 OK):
A nova categoria foi criada com sucesso. A resposta contém as informações da categoria criada.

Resposta de Erro (404 Not Found):
Se houver algum problema ao criar a nova categoria, uma resposta de erro será retornada.

###Obter Todas as Categorias
Método: GET /categories

Este endpoint permite obter a lista de todas as categorias cadastradas.

**Exemplo de Requisição:**
GET /categories

Resposta de Sucesso (200 OK):
A resposta contém uma lista de todas as categorias cadastradas.

Cada categoria na lista possui as seguintes informações:

id (Long): O ID da categoria.
name (String): O nome da categoria.
**Exemplo de Resposta:**
GET /categories
```json[
    {
        "id": 1,
        "name": "Categoria 1"
    },
    {
        "id": 2,
        "name": "Categoria 2"
    }
]
```json


