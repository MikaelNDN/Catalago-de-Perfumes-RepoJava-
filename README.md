# 🌸 MCosmeticos - Catálogo Digital

Sistema de gerenciamento e catálogo de perfumes desenvolvido para facilitar a vitrine de produtos e integração com vendas via WhatsApp.

## 🚀 Tecnologias Utilizadas

* **Java 17** & **Spring Boot 3** (Backend)
* **PostgreSQL** (Banco de Dados na Nuvem/Railway)
* **Thymeleaf** (Frontend e Renderização)
* **Cloudinary** (Armazenamento de Imagens na Nuvem)
* **Bootstrap** (Estilização Responsiva)

## ⚙️ Funcionalidades

* Cadastro, Edição e Exclusão de Perfumes (Área Administrativa).
* Upload de imagens com redimensionamento automático via Cloudinary.
* Catálogo público para clientes.
* Botão "Comprar" integrado com a API do WhatsApp.
* Segurança de dados sensíveis via Variáveis de Ambiente.

## 🛠️ Como Rodar o Projeto

Para rodar este projeto localmente, você precisa configurar as variáveis de ambiente no seu IDE ou Sistema Operacional:

* `DB_PASSWORD`: Senha do Banco PostgreSQL.
* `ADMIN_PASSWORD`: Senha para acessar o painel /admin.
* `CLOUDINARY_CLOUD_NAME`: Seu Cloud Name.
* `CLOUDINARY_API_KEY`: Sua API Key.
* `CLOUDINARY_API_SECRET`: Sua API Secret.