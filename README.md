# Gerenciador de Tarefas - API REST Full-Stack

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)
![Docker](https://img.shields.io/badge/Docker-blue)
![Terraform](https://img.shields.io/badge/Terraform-purple)
![AWS](https://img.shields.io/badge/AWS-orange)
![Status](https://img.shields.io/badge/status-Concluído-green)

## 📖 Descrição

O **Gerenciador de Tarefas** é uma API RESTful robusta e segura, projetada para servir como o backend de uma aplicação de lista de tarefas (To-Do list). Este projeto foi construído utilizando uma stack de tecnologias modernas e práticas de DevOps, demonstrando um fluxo de trabalho completo desde o desenvolvimento local até o deploy automatizado na nuvem.

O objetivo principal deste projeto é servir como uma peça de portfólio, exibindo competências em desenvolvimento backend com Java e Spring Boot, segurança de APIs com JWT, containerização com Docker e provisionamento de infraestrutura como código (IaC) na AWS com Terraform.

## ✨ Principais Funcionalidades

- **Autenticação Segura:** Sistema de registro e login com senhas criptografadas e autenticação baseada em Tokens JWT (JSON Web Tokens).
- **Gerenciamento de Tarefas:** Endpoints protegidos para criar e listar tarefas, com cada tarefa associada ao usuário autenticado.
- **Modelo de Dados Relacional:** Persistência de dados em um banco de dados PostgreSQL, gerenciado pelo Spring Data JPA.
- **Documentação de API Interativa:** Geração automática de documentação com Swagger (Springdoc), permitindo fácil visualização e teste dos endpoints.
- **Infraestrutura como Código (IaC):** Todo o ambiente de nuvem (rede, banco de dados, serviço de aplicação) é versionado e provisionado de forma automatizada com Terraform.
- **Pronto para Contêineres:** A aplicação é totalmente containerizada com Docker, garantindo consistência entre os ambientes de desenvolvimento e produção.

## 🛠️ Stack de Tecnologias

A arquitetura do projeto foi dividida nas seguintes camadas:

#### Backend
- **Java 17**: Linguagem principal da aplicação.
- **Spring Boot 3**: Framework central para a criação da API.
- **Spring Web**: Para a criação dos controllers e endpoints REST.
- **Spring Security**: Para implementação da autenticação e autorização com JWT.
- **Spring Data JPA (Hibernate)**: Para o mapeamento objeto-relacional e persistência de dados.
- **Maven**: Gerenciador de dependências e build do projeto.

#### Banco de Dados
- **PostgreSQL**: Banco de dados relacional para o ambiente de produção e desenvolvimento.

#### DevOps & Cloud
- **Docker & Docker Compose**: Para containerização da aplicação e orquestração do ambiente de desenvolvimento local.
- **Terraform**: Para automação do provisionamento da infraestrutura na nuvem (Infraestrutura como Código).
- **Amazon Web Services (AWS)**: Provedor de nuvem para o deploy.
  - **ECR (Elastic Container Registry)**: Armazenamento da imagem Docker da aplicação.
  - **RDS (Relational Database Service)**: Banco de dados PostgreSQL gerenciado.
  - **ECS (Elastic Container Service) com Fargate**: Execução do contêiner da aplicação sem gerenciamento de servidores.
  - **ALB (Application Load Balancer)**: Distribuição de tráfego para a aplicação.
  - **VPC (Virtual Private Cloud)**: Rede virtual isolada para os recursos.

#### Qualidade e Ferramentas
- **Springdoc (Swagger UI)**: Geração automática da documentação da API.
- **Git & GitHub**: Controle de versão e hospedagem do código.

## 🚀 Como Executar

Existem duas formas de executar este projeto: localmente com Docker (recomendado para testes) ou fazendo o deploy completo na AWS com Terraform.

### Pré-requisitos
- Java 17+
- Maven 3.8+
- Docker & Docker Compose
- AWS CLI (configurado com suas credenciais)
- Terraform

### 1. Execução Local com Docker

Este método sobe a API e um banco de dados PostgreSQL em contêineres na sua máquina local.

1.  **Clone o repositório:**
    ```sh
    git clone [https://github.com/seu-usuario/gerenciador-tarefas.git](https://github.com/seu-usuario/gerenciador-tarefas.git)
    cd gerenciador-tarefas
    ```

2.  **Suba os contêineres:**
    O `docker-compose.yml` já está configurado. Execute o seguinte comando na raiz do projeto:
    ```sh
    docker-compose up --build
    ```

3.  **Acesse a API:**
    - A API estará disponível em `http://localhost:8080`.
    - A documentação interativa do Swagger UI estará disponível em: `http://localhost:8080/swagger-ui/index.html`

### 2. Deploy na Nuvem com Terraform

Este método provisiona toda a infraestrutura necessária na AWS e faz o deploy da aplicação.

**Atenção:** Este processo criará recursos na sua conta AWS que podem gerar custos.

1.  **Navegue até a pasta do Terraform:**
    ```sh
    cd terraform
    ```

2.  **Configure as credenciais do banco de dados:**
    Crie um arquivo chamado `terraform.tfvars` e preencha com o usuário e senha desejados para o banco de dados RDS.
    ```hcl
    # terraform/terraform.tfvars

    db_username = "usuario_do_banco"
    db_password = "SuaSenhaSuperForte123!"
    ```

3.  **Inicialize o Terraform:**
    ```sh
    terraform init
    ```

4.  **Planeje e Aplique a Infraestrutura:**
    ```sh
    terraform plan
    terraform apply
    ```
    Confirme a aplicação digitando `yes` quando solicitado.

5.  **Acesse a API na Nuvem:**
    Após a conclusão do `apply`, o Terraform exibirá o endereço DNS do Load Balancer nos outputs. Este será o endereço público da sua API na nuvem.