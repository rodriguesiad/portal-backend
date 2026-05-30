# Portal de Editais — Backedn

Este projeto é a API do desafio FAPTGULHAS - HACKAMARH . Seu objetivo é democratizar o acesso ao mercado de carbono jurisdicional (JREDD+) no Tocantins.A plataforma atua como uma ponte de alta integridade que conecta proponentes aos fundos globais, garantindo segurança jurídica, transparência matemática e blindagem contra fraudes e greenwashing por meio de evidências georreferenciadas auditáveis e automações de triagem.

---

## 🔧 Tecnologias utilizadas

| Tecnologia | Versão |
|-------------|--------|
| Spring Boot   | 3.5.8  |
| Java        | 21     |
| MySQL       | 8      |
| Docker      | ✅     |

---

## 🐳 Como executar o projeto com Docker
### 🚀 Subindo a aplicação e o banco de dados:

Execute o comando:

```bash
docker-compose up --build
```

Isso irá subir:

- 🔸 **MySQL** na porta `3309` (externa) e `3306` (interna no container).
- 🔸 **API Micronaut** na porta `8282`.

---
## 🔗 📖 Documentação da API (Swagger)

Após iniciar a aplicação, acesse:

http://localhost:8282/swagger-ui/index.html

ou

http://localhost:8282/swagger-ui.html

para visualizar e testar os endpoints da API. 

---

## 🔐 Autenticação

- Todos os endpoints (exceto cadastro de usuário e login) exigem autenticação via token JWT.



