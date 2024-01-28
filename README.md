# video
Projeto Video Tech FIAP
markdown
Copy code
# Documentação da API de Vídeos

## Endpoints

### Obter Todos os Vídeos

GET /api/videos

Exemplo de Resposta (200 OK)
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
]```
###Obter Vídeo por ID
Retorna um vídeo específico pelo seu ID.

Parâmetros de URL
{id} (Long): O ID do vídeo que deseja obter.

Exemplo de Resposta (200 OK)
GET /api/videos/{id}

json
Copy code
{
    "id": 1,
    "title": "Vídeo 1",
    "description": "Descrição do Vídeo 1",
    "url": "https://www.example.com/video1",
    "publicationDate": "2022-01-28T10:00:00Z",
    "favorite": true,
    "categoryId": 1
}
Marcar/Desmarcar Vídeo como Favorito
Altera o status de favorito de um vídeo específico.

Parâmetros de URL
{videoId} (Long): O ID do vídeo que deseja marcar/desmarcar como favorito.

Exemplo de Requisição:
PATCH /api/videos/{videoId}

Exemplo de Resposta (200 OK)

json
Copy code
{
    "message": "Vídeo marcado como favorito com sucesso."
}
Listar Vídeos por Categoria
GET /api/videos/category/{categoryId}

Retorna uma lista de vídeos pertencentes a uma categoria específica.

Parâmetros de URL
{categoryId} (Long): O ID da categoria pela qual deseja filtrar os vídeos.

Exemplo de Requisição:
GET /api/videos/category/1

json
Copy code
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
Listar Vídeos Favoritos
GET /api/videos/favorites

Retorna uma lista de todos os vídeos marcados como favoritos.

Exemplo de Requisição
GET /api/videos/favorites

Exemplo de Resposta (200 OK):

json
Copy code
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
Criar Novo Vídeo
POST /api/videos

Cria um novo vídeo.

Corpo da Requisição:

json
Copy code
{
    "title": "Novo Vídeo",
    "description": "Descrição do Novo Vídeo",
    "url": "https://www.example.com/novovideo",
    "publicationDate": "2022-01-28T14:00:00Z",
    "favorite": false,
    "categoryId": 1
}
Exemplo de Requisição
POST /api/videos

Exemplo de Resposta (201 Created)

json
Copy code
{
    "id": 5,
    "title": "Novo Vídeo",
    "description": "Descrição do Novo Vídeo",
    "url": "https://www.example.com/novovideo",
    "publicationDate": "2022-01-28T14:00:00Z",
    "favorite": false,
    "categoryId": 1
}
Excluir Vídeo
DELETE /api/videos/{id}

Exclui um vídeo específico.

Parâmetros de URL
{id} (Long): O ID do vídeo que deseja excluir.

Exemplo de Requisição
DELETE /api/videos/1

Exemplo de Resposta (204 No Content)
Não há corpo de resposta.

Obter Estatísticas dos Vídeos
GET /api/videos/statistics

Retorna estatísticas dos vídeos, incluindo o número total de favoritos e a média de visualizações.

Exemplo de Requisição:
GET /api/videos/statistics

Exemplo de Resposta (200 OK)

json
Copy code
{
    "totalFavorites": 3,
    "averageViews": 1000
}
