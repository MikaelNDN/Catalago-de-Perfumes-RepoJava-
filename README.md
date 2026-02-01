# 🌸 MCosmeticos - Catálogo Digital

> **Acesse o projeto online:** [Clique aqui para ver a Demo](https://catalago-de-perfumes-production.up.railway.app/)

Sistema de gerenciamento e catálogo de perfumes desenvolvido para facilitar a vitrine de produtos e integração com vendas via WhatsApp. O projeto foca em performance, segurança e uma experiência mobile-first.

## 🚀 Tecnologias Utilizadas

* **Java 17** & **Spring Boot 3** (Backend Robusto)
* **PostgreSQL** (Banco de Dados e Armazenamento na Nuvem Railway)
* **Spring Security** (Autenticação e Proteção de Rotas)
* **Cloudinary** (Armazenamento de Imagens na Nuvem
* **Thymeleaf** (Renderização Server-Side)
* **Bootstrap** (Estilização Responsiva)
* **Docker** (Containerização para Deploy)

## ⚙️ Funcionalidades

* **Painel Administrativo:** Cadastro, Edição e Exclusão de Perfumes (Protegido por senha).
* **Gestão de Imagens:** Upload de imagens com redimensionamento automático via Cloudinary.
* **Vitrine Virtual:** Catálogo público acessível para clientes.
* **Integração WhatsApp:** Botão "Comprar" que direciona o pedido já preenchido para o WhatsApp do vendedor.
* **Segurança:** Dados sensíveis protegidos via Variáveis de Ambiente.

## 🛠️ Como Rodar o Projeto

Para rodar este projeto localmente, você precisa configurar as variáveis de ambiente no seu IDE (IntelliJ/Eclipse) ou criar um arquivo de configuração:

### Variáveis Necessárias:
* `DATABASE_URL`: URL de conexão JDBC (Ex: jdbc:postgresql://localhost:5432/mcosmeticos)
* `DATABASE_USERNAME`: Seu usuário do Postgres
* `DATABASE_PASSWORD`: Sua senha do Postgres

---
*Desenvolvido por [Mikael Noberto]*
