<div align="center">

```
███████╗ ██████╗ ██████╗ ██╗   ██╗███████╗██████╗ ████████╗██╗ ██████╗███████╗
██╔════╝██╔════╝██╔═══██╗██║   ██║██╔════╝██╔══██╗╚══██╔══╝██║██╔════╝██╔════╝
█████╗  ██║     ██║   ██║██║   ██║█████╗  ██████╔╝   ██║   ██║██║     █████╗  
██╔══╝  ██║     ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗   ██║   ██║██║     ██╔══╝  
███████╗╚██████╗╚██████╔╝ ╚████╔╝ ███████╗██║  ██║   ██║   ██║╚██████╗███████╗
╚══════╝ ╚═════╝ ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝╚══════╝
    ████████╗ ██████╗  ██████╗ █████╗ ███╗  ██╗████████╗██╗███╗  ██╗███████╗
       ██╔══╝██╔═══██╗██╔════╝██╔══██╗████╗ ██║╚══██╔══╝██║████╗ ██║██╔════╝
       ██║   ██║   ██║██║     ███████║██╔██╗██║   ██║   ██║██╔██╗██║███████╗
       ██║   ██║   ██║██║     ██╔══██║██║╚████║   ██║   ██║██║╚████║╚════██║
       ██║   ╚██████╔╝╚██████╗██║  ██║██║ ╚███║   ██║   ██║██║ ╚███║███████║
       ╚═╝    ╚═════╝  ╚═════╝╚═╝  ╚═╝╚═╝  ╚══╝   ╚═╝   ╚═╝╚═╝  ╚══╝╚══════╝
```

# 🌿 EcoVertice Tocantins — API

**Plataforma integrada de rastreabilidade e democratização de créditos de carbono**

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.8-6DB33F?style=for-the-badge&logo=springboot)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=for-the-badge&logo=mysql)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker)
![JWT](https://img.shields.io/badge/JWT-Authentication-000000?style=for-the-badge&logo=jsonwebtokens)

</div>

---

## 🌱 Sobre o Projeto

A **EcoVertice Tocantins** é uma solução tecnológica integrada de ponta a ponta criada para **democratizar o acesso** e garantir **total gestão** aos processos que geram fundos de créditos de carbono. Estruturada funcionalmente em **cinco módulos principais**, a plataforma unifica a jornada do recurso ambiental do registro ao beneficiário final com transparência, segurança e acessibilidade.

> 🌳 *Tecnologia a serviço da floresta. Inovação a serviço das pessoas.*

---

## 🏗️ Arquitetura & Tecnologias

### Dependências Principais

| Tecnologia | Versão | Descrição |
|------------|---------|-----------|
| **Java** | 21 | Linguagem principal da aplicação |
| **Spring Boot** | 3.5.8 | Framework principal da API |
| **Spring Security** | Integrado | Controle de autenticação e autorização |
| **JWT** | Integrado | Autenticação baseada em tokens |
| **MySQL** | 8 | Banco de dados relacional |
| **Docker** | Latest | Containerização da aplicação |
| **Swagger/OpenAPI** | Integrado | Documentação dos endpoints |
| **Maven** | Wrapper | Gerenciamento de dependências e build |

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos

Antes de começar, certifique-se de possuir:

- Java 21
- Maven 3.9+
- Docker e Docker Compose (opcional)
- Git

---

### Passo a Passo

#### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd portal-editais-api
```

#### 2. Configure as variáveis de ambiente

Crie ou ajuste o arquivo de configuração conforme o ambiente utilizado.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/portal_editais
spring.datasource.username=root
spring.datasource.password=senha
```

#### 3. Execute a aplicação

##### Com Maven

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

##### Com Docker

```bash
docker-compose up --build
```

---

### Serviços Disponíveis

Ao iniciar utilizando Docker, os seguintes serviços serão disponibilizados:

| Serviço | Porta |
|----------|--------|
| API REST | 8282 |
| MySQL (Host) | 3309 |
| MySQL (Container) | 3306 |

---

### Acesse a Aplicação

#### API

```text
http://localhost:8282
```

#### Swagger UI

```text
http://localhost:8282/swagger-ui/index.html
```

ou

```text
http://localhost:8282/swagger-ui.html
```

---

### Scripts Disponíveis

| Comando | Descrição |
|----------|-----------|
| `./mvnw spring-boot:run` | Executa a aplicação em desenvolvimento |
| `./mvnw clean package` | Gera o pacote da aplicação |
| `./mvnw test` | Executa os testes |
| `docker-compose up --build` | Sobe aplicação e banco via Docker |
| `docker-compose down` | Encerra os containers |

---

## ✅ Checklist de Objetivos

### 🏛️ Módulos da Plataforma

- [x] Cadastro e autenticação de usuários
- [x] Gestão de perfis e permissões (RBAC)
- [x] Submissão e gerenciamento de projetos
- [x] Fluxo de análise e aprovação de editais
- [x] Registro de evidências e documentos
- [x] Auditoria e rastreabilidade de ações
- [x] API REST documentada via OpenAPI

### ⚙️ Infraestrutura & Qualidade

- [x] Containerização com Docker
- [x] Persistência em banco relacional
- [x] Tratamento centralizado de exceções
- [x] Validação de requisições
- [ ] Pipeline CI/CD
- [ ] Testes automatizados de integração

### 🔐 Segurança

- [x] Autenticação JWT
- [x] Controle de acesso baseado em papéis (RBAC)
- [x] Criptografia de senhas
- [x] Auditoria de operações críticas
- [x] Proteção de endpoints privados

---

## 📁 Estrutura do Projeto

```text
portal-editais-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   │   └── docker/
│   └── test/
├── docs/
├── docker-compose.yml
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## 🔐 Autenticação

A API utiliza autenticação baseada em **JWT (JSON Web Token)**.

### Endpoints Públicos

- Cadastro de usuário
- Login
- Recuperação de acesso (quando habilitado)

### Endpoints Protegidos

Todos os demais endpoints exigem envio do token JWT:

```http
Authorization: Bearer <token>
```

---

## 📖 Documentação da API

A documentação completa dos endpoints está disponível através do Swagger/OpenAPI.

Após iniciar a aplicação, acesse:

```text
http://localhost:8282/swagger-ui/index.html
```

---

## 🤝 Contribuindo

Contribuições são muito bem-vindas.

1. Faça um fork do projeto
2. Crie uma branch para sua feature

```bash
git checkout -b feature/minha-feature
```

3. Realize suas alterações
4. Faça commit utilizando Conventional Commits

```bash
git commit -m "feat: adiciona nova funcionalidade"
```

5. Envie para seu fork

```bash
git push origin feature/minha-feature
```

6. Abra um Pull Request

> 💡 Utilize o padrão Conventional Commits para manter o histórico organizado.

---

## 📄 Licença

Este projeto está sob licença privada. Todos os direitos reservados © EcoVertice Tocantins.

---

## 🤖 Aviso sobre Inteligência Artificial

> Este projeto foi desenvolvido com o auxílio de ferramentas de Inteligência Artificial generativa para apoio na modelagem, implementação, revisão de código, documentação técnica e automação de tarefas de desenvolvimento.
>
> O uso dessas ferramentas ocorreu sob supervisão da equipe responsável, que manteve a responsabilidade integral pelas decisões arquiteturais, validação das regras de negócio, segurança e qualidade do software.

---

<div align="center">

**Feito com 💚 para o Tocantins e para o planeta**

🌿 *Cada linha de código, um passo pela floresta.*

</div>
