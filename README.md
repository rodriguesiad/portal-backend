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

- Git

---

### 1. Clone o repositório (obrigatório)

```bash
git clone <url-do-repositorio>
cd portal-editais-api
```

A clonagem é necessária tanto para execução via Docker quanto para execução local, pois o `docker-compose` e as configurações da aplicação fazem parte do projeto.

---

### 🐳 Forma recomendada (Docker)

A aplicação já está configurada para execução completa via Docker, incluindo banco de dados e API.

```bash
docker-compose up --build
```

Isso irá subir:

- 🔸 **API Spring Boot** na porta `8282`
- 🔸 **MySQL** na porta `3309` (host) / `3306` (container)

---

### 🖥️ Execução local (opcional)

Caso deseje executar diretamente na máquina (fora do Docker), é necessário configurar o ambiente manualmente.

### Pré-requisitos

- Java 21
- Maven 3.9+
- MySQL 8
- Git

---

#### 1. Iniciar o MySQL local

Antes de iniciar a aplicação, certifique-se de que o serviço MySQL está rodando na sua máquina.

A porta padrão utilizada é:

```
3306
```

Caso o seu MySQL esteja rodando em outra porta, você deve ajustar a URL de conexão.

---

#### 2. Criar a base de dados

No seu MySQL local, crie o banco de dados manualmente:

```sql
CREATE DATABASE portaldb;
```

---

#### 3. Configurar o `application.properties`

Ajuste as credenciais conforme seu ambiente local:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/portaldb
spring.datasource.username=root
spring.datasource.password=senha
```

📌 Observações:
- `localhost:3306` → pode variar conforme a porta configurada no seu MySQL local
- `portaldb` → deve existir previamente no seu banco
- `username/password` → devem ser os mesmos do seu ambiente MySQL

---
#### 3. Execute a aplicação

Antes de iniciar a aplicação, execute o build completo do projeto:

```bash
./mvnw clean install
```

ou

```bash
mvn clean install
```

Em seguida, inicie a aplicação:

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

## 📖 Acesso à aplicação

### Serviços Disponíveis

Ao iniciar utilizando Docker, os seguintes serviços serão disponibilizados:

| Serviço | Porta |
|----------|--------|
| API REST | 8282 |
| MySQL (Host) | 3309 |
| MySQL (Container) | 3306 |

---

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

## ✅ Checklist de Objetivos

### 🏛️ Módulos da Plataforma

- [x] Cadastro e autenticação de usuários
- [x] Gestão de perfis e permissões
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
│   │   ├── java/portal/editais/
|   |    ├── config/
|   |    |   ├── controller/
|   |    |   ├── dto/
|   |    |   ├── entity/
|   |    |   ├── repository/
|   |    |   ├── service/
|   |    |   ├── specification/
|   |    |   ├── validations/
│   │   ├── resources/
├── .env
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## 🔐 Autenticação

A API utiliza autenticação baseada em **JWT (JSON Web Token)**.

### 👤 Perfis de acesso (Roles)

O sistema possui os seguintes perfis de usuário:

| Perfil | Responsabilidade |
|--------|------------------|
| **PROPONENTE** | Responsável submeter projetos aos editais |
| **ADMINISTRADOR** | Responsável por gerenciar editais |
| **AVALIADOR** | Responsável por analisar e avaliar os projetos submetidos |
| **AUDITOR** | Responsável por acompanhar, fiscalizar e auditar os projetos e seus registros |

---

### 🔑 Usuários de exemplo (data.sql)

O sistema já possui usuários previamente cadastrados para testes:

- Perfis disponíveis:
  - PROPONENTE
  - ADMINISTRADOR
  - AVALIADOR

- Senha padrão de todos os usuários de exemplo:
```
12345678
```

---

### Endpoints Públicos

- Cadastro de usuário PROPONENTE
- Login
- Listagem de Editais

---


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

## 📄 Licença

Este projeto está sob licença privada. Todos os direitos reservados © EcoVertice Tocantins.

---

## 🤖 Uso de Inteligência Artificial no Desenvolvimento (Backend)

> O backend do sistema utilizou ferramentas de Inteligência Artificial generativa de forma pontual e assistida, com foco em aceleração de tarefas técnicas, geração de dados iniciais e apoio à estruturação de funcionalidades.

### 🛠️ Ferramentas Utilizadas

| Ferramenta | Papel no Projeto |
|------------|-----------------|
| **ChatGPT (OpenAI - versão online)** | Suporte em dúvidas técnicas e geração de dados iniciais para `data.sql` |
| **ChatGPT (Codex)** | Auxílio na criação de algumas das estruturas de CRUD, endpoints REST e serviços Spring Boot. Apoio na configuração e implementação do servidor de arquivos e integração com MinIO |

---

### 💬 Exemplos de Prompts Utilizados

#### 🗄️ Geração de dados iniciais (data.sql)

```
Gere INSERTs SQL com dados válidos e que façam sentido para fazer nessa tabela:

[SQL da tabela utilizado]

Requisitos:
- Dados fictícios, mas realistas e consistentes com o domínio ambiental e REDD+
- Respeitar tipos de dados e constraints
- Garantir coerência entre registros

```

---

#### 📦 Integração com servidor de arquivos (MinIO)

```
Estou usando Spring Boot com MinIO rodando via Docker.

Configure:
- Upload de arquivos para MinIO
- Endpoint para upload de documentos de editais
- Download público via URL assinada ou endpoint protegido
- Persistência da referência do arquivo no banco de dados
- Suporte a múltiplos arquivos por edital
```

---

### ⚠️ Supervisão Humana

Todo uso de Inteligência Artificial no backend foi realizado como ferramenta de apoio ao desenvolvimento.

A equipe manteve responsabilidade total sobre:
- Arquitetura do sistema
- Regras de negócio
- Segurança e autenticação
- Qualidade e validação do código gerado

O objetivo do uso de IA foi **acelerar a implementação de estruturas repetitivas e técnicas**, mantendo controle humano sobre decisões críticas do sistema.

---

<div align="center">

**Feito com 💚 para o Tocantins e para o planeta**

🌿 *Cada linha de código, um passo pela floresta.*

</div>
