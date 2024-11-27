# Sistema de Gestão de Pedidos para Restaurantes - Microsserviços

## Descrição
Este projeto é uma refatoração de um sistema monolítico de gestão de pedidos para restaurantes em uma arquitetura de microsserviços. O sistema permite gerenciar clientes e pedidos de forma distribuída e escalável.

## Estrutura do Projeto 
restaurante-system/
├── cliente-service/ # Serviço de gestão de clientes
├── pedido-service/ # Serviço de gestão de pedidos
└── docker-compose.yml # Configuração dos containers

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.1.5
- PostgreSQL
- Docker
- Maven
- Lombok
- Spring Data JPA

## Pré-requisitos
- Docker e Docker Compose instalados
- Java 17 ou superior
- Maven

## Como Executar

1. Clone o repositório:
```bash
git clone [url-do-repositorio]
cd restaurante-system
```

2. Compile os serviços:
```bash
cd cliente-service
./mvnw clean package
cd ../pedido-service
./mvnw clean package
cd ..
```

3. Inicie os containers:
```bash
docker-compose up --build
```

## Endpoints Disponíveis

### Cliente Service (porta 8081)

- Criar Cliente:
  POST http://localhost:8081/clientes
  ```json
  {
    "nome": "João Silva",
    "email": "joao@email.com",
    "telefone": "11999999999"
  }
  ```

- Listar Clientes:
  GET http://localhost:8081/clientes

- Buscar Cliente por ID:
  GET http://localhost:8081/clientes/{id}

### Pedido Service (porta 8082)

- Criar Pedido:
  POST http://localhost:8082/pedidos
  ```json
  {
    "clienteId": 1,
    "valorTotal": 50.00
  }
  ```

- Listar Pedidos:
  GET http://localhost:8082/pedidos

- Buscar Pedido por ID:
  GET http://localhost:8082/pedidos/{id}

## Arquitetura

### Microsserviços
- Cliente Service: Responsável pelo gerenciamento de clientes
- Pedido Service: Responsável pelo gerenciamento de pedidos

### Banco de Dados
- PostgreSQL compartilhado (em produção, recomenda-se bancos separados)
- Cada serviço possui seu próprio schema

## Benefícios da Refatoração

1. Escalabilidade
   - Serviços podem escalar independentemente
   - Melhor utilização de recursos

2. Manutenibilidade
   - Código mais organizado
   - Facilidade para atualizações
   - Menor acoplamento

3. Resiliência
   - Falhas isoladas por serviço
   - Maior disponibilidade do sistema

4. Desenvolvimento
   - Times podem trabalhar independentemente
   - Deploy mais rápido e seguro

## Como Testar

1. Criar um cliente:
```bash
curl -X POST http://localhost:8081/clientes \
-H "Content-Type: application/json" \
-d '{
    "nome": "Maria Silva",
    "email": "maria@email.com",
    "telefone": "11988887777"
}'
```

2. Criar um pedido:
```bash
curl -X POST http://localhost:8082/pedidos \
-H "Content-Type: application/json" \
-d '{
    "clienteId": 1,
    "valorTotal": 150.00
}'
```

## Parando o Sistema
```bash
docker-compose down
```

## Considerações de Produção

Para um ambiente de produção, considere:
1. Implementar autenticação e autorização
2. Adicionar logs e monitoramento
3. Configurar backups
4. Implementar circuit breakers
5. Adicionar documentação da API (Swagger)
6. Implementar testes automatizados
7. Configurar CI/CD

## Suporte

Para questões e suporte:
- Abra uma issue no repositório
- Entre em contato com a equipe de desenvolvimento

## Contribuindo

1. Fork o projeto
2. Crie sua feature branch
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## Licença
Este projeto está sob a licença MIT.

---
Desenvolvido como parte do projeto de refatoração de sistemas legados.
