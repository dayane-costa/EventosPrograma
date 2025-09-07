# Sistema de Eventos

Sistema de registro e notificação de eventos desenvolvido em Java seguindo o paradigma orientado a objetos.

## Funcionalidades

### ✅ Requisitos Implementados

- **Paradigma Orientado a Objetos**: Todo o sistema foi desenvolvido seguindo OOP
- **Registro de Usuários**: Sistema completo com mais de 3 atributos (nome, email, telefone, data de nascimento, endereço)
- **Registro de Eventos**: Com todos os atributos obrigatórios (nome, endereço, categoria, horário, descrição)
- **Categorias de Eventos**: 13 categorias predefinidas (festas, eventos esportivos, shows, etc.)
- **Consulta de Eventos**: Visualização de todos os eventos, futuros, acontecendo agora e passados
- **Participação em Eventos**: Confirmação e cancelamento de participação
- **Ordenação Temporal**: Eventos ordenados por proximidade
- **Verificação de Status**: Identifica eventos em andamento, futuros e passados
- **Persistência**: Dados salvos em arquivo `events.data`
- **Carregamento Automático**: Eventos carregados automaticamente na inicialização
- **Interface Console**: Menu interativo completo

### 🎯 Funcionalidades Extras

- Sistema de login/logout
- Busca por categoria e nome
- Estatísticas do sistema
- Visualização de eventos públicos (sem login)
- Criação de eventos pelos usuários
- Controle de participantes por evento

## Estrutura do Projeto

```
├── SistemaEventos.java          # Classe principal com interface console
├── GerenciadorEventos.java      # Gerencia eventos e persistência
├── GerenciadorUsuarios.java     # Gerencia usuários e autenticação
├── Usuario.java                 # Modelo de usuário
├── Evento.java                  # Modelo de evento
├── CategoriaEvento.java         # Enum com categorias
├── DiagramaClasses.md           # Diagrama de classes
└── README.md                    # Este arquivo
```

## Como Executar

1. **Compilar o projeto**:
   ```bash
   javac *.java
   ```

2. **Executar o sistema**:
   ```bash
   java SistemaEventos
   ```

## Arquivos de Dados

O sistema cria automaticamente os seguintes arquivos:
- `events.data`: Armazena todos os eventos
- `usuarios.data`: Armazena todos os usuários

## Categorias de Eventos Disponíveis

1. Festa
2. Evento Esportivo
3. Show
4. Conferência
5. Workshop
6. Exposição
7. Teatro
8. Cinema
9. Gastronomia
10. Cultural
11. Religioso
12. Educacional
13. Outros

## Menu Principal

### Sem Login:
- Fazer Login
- Registrar Usuário
- Ver Eventos (público)
- Sair

### Com Login:
- Ver Todos os Eventos
- Ver Eventos Futuros
- Ver Eventos Acontecendo Agora
- Ver Eventos Passados
- Buscar por Categoria
- Buscar por Nome
- Participar de Evento
- Ver Meus Eventos
- Cancelar Participação
- Criar Novo Evento
- Ver Estatísticas
- Logout

## Padrões Arquiteturais

- **MVC (Model-View-Controller)**: Separação clara de responsabilidades
- **Repository Pattern**: Gerenciadores atuam como repositórios de dados
- **Singleton Pattern**: Uma instância de cada gerenciador por sistema

## Exemplo de Uso

1. Execute o sistema
2. Registre um novo usuário
3. Faça login
4. Crie alguns eventos
5. Participe de eventos
6. Visualize seus eventos
7. Teste as funcionalidades de busca

## Tecnologias Utilizadas

- Java 8+
- LocalDateTime para controle temporal
- File I/O para persistência
- Scanner para entrada de dados
- Collections Framework para gerenciamento de listas

## Desenvolvido por

Sistema desenvolvido seguindo as especificações do projeto acadêmico, implementando todas as funcionalidades solicitadas com código limpo e bem estruturado.

