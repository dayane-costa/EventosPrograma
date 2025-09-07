import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class SistemaEventos {
    private GerenciadorEventos gerenciadorEventos;
    private GerenciadorUsuarios gerenciadorUsuarios;
    private Scanner scanner;
    private DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public SistemaEventos() {
        this.gerenciadorEventos = new GerenciadorEventos();
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciar() {
        System.out.println("=== SISTEMA DE EVENTOS ===");
        System.out.println("Bem-vindo ao sistema de registro e notificação de eventos!");
        System.out.println("==========================================================");
        
        while (true) {
            if (!gerenciadorUsuarios.isUsuarioLogado()) {
                menuPrincipal();
            } else {
                menuUsuario();
            }
        }
    }
    
    private void menuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Fazer Login");
        System.out.println("2. Registrar Usuário");
        System.out.println("3. Ver Eventos (sem login)");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        System.out.print("\n==========================\n");
        
        switch (opcao) {
            case 1:
                fazerLogin();
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                verEventosPublicos();
                break;
            case 4:
                System.out.println("Obrigado por usar o sistema!");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private void menuUsuario() {
        Usuario usuario = gerenciadorUsuarios.getUsuarioLogado();
        System.out.println("\n======== MENU USUÁRIO ========");
        System.out.println("Usuário logado: " + usuario.getNome());
        System.out.println("1. Ver Todos os Eventos");
        System.out.println("2. Ver Eventos Futuros");
        System.out.println("3. Ver Eventos Acontecendo Agora");
        System.out.println("4. Ver Eventos Passados");
        System.out.println("5. Buscar Eventos por Categoria");
        System.out.println("6. Buscar Eventos por Nome");
        System.out.println("7. Participar de um Evento");
        System.out.println("8. Ver Meus Eventos");
        System.out.println("9. Cancelar Participação");
        System.out.println("10. Criar Novo Evento");
        System.out.println("11. Ver Estatísticas");
        System.out.println("12. Logout");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                verTodosEventos();
                break;
            case 2:
                verEventosFuturos();
                break;
            case 3:
                verEventosAcontecendo();
                break;
            case 4:
                verEventosPassados();
                break;
            case 5:
                buscarPorCategoria();
                break;
            case 6:
                buscarPorNome();
                break;
            case 7:
                participarEvento();
                break;
            case 8:
                verMeusEventos();
                break;
            case 9:
                cancelarParticipacao();
                break;
            case 10:
                criarEvento();
                break;
            case 11:
                verEstatisticas();
                break;
            case 12:
                gerenciadorUsuarios.fazerLogout();
                System.out.println("Logout realizado com sucesso!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
        System.out.println("\n==========================");
    }
    
    private void registrarUsuario() {
        System.out.println("\n=== REGISTRO DE USUÁRIO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        LocalDate dataNascimento = lerData();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        Usuario novoUsuario = new Usuario(nome, email, telefone, dataNascimento, endereco);
        
        if (gerenciadorUsuarios.registrarUsuario(novoUsuario)) {
            System.out.println("Usuário registrado com sucesso!");
        } else {
            System.out.println("Erro: Email já existe!");
        }
        System.out.println("\n==========================");
    }
    private void fazerLogin() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        if (gerenciadorUsuarios.fazerLogin(email)) {
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Email não encontrado!");
        }
        System.out.println("\n==========================");
    }
    
    private void verEventosPublicos() {
        System.out.println("\n=== EVENTOS PÚBLICOS ===");
        List<Evento> eventos = gerenciadorEventos.getEventosOrdenadosPorProximidade();
        
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
        System.out.println("\n==========================");
    }
    
    private void verTodosEventos() {
        System.out.println("\n=== TODOS OS EVENTOS ===");
        List<Evento> eventos = gerenciadorEventos.getEventosOrdenadosPorProximidade();
        
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
    }
    
    private void verEventosFuturos() {
        System.out.println("\n=== EVENTOS FUTUROS ===");
        List<Evento> eventos = gerenciadorEventos.getEventosFuturos();
        
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento futuro cadastrado.");
            return;
        }
        
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
    }
    
    private void verEventosAcontecendo() {
        System.out.println("\n=== EVENTOS ACONTECENDO AGORA ===");
        List<Evento> eventos = gerenciadorEventos.getEventosAcontecendo();
        
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento acontecendo no momento.");
            return;
        }
        
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
    }
    
    private void verEventosPassados() {
        System.out.println("\n=== EVENTOS PASSADOS ===");
        List<Evento> eventos = gerenciadorEventos.getEventosPassados();
        
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento passado cadastrado.");
            return;
        }
        
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
    }
    
    private void buscarPorCategoria() {
        System.out.println("\n=== BUSCAR POR CATEGORIA ===");
        System.out.println("Categorias disponíveis:");
        
        CategoriaEvento[] categorias = CategoriaEvento.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i].getDescricao());
        }
        
        System.out.print("Escolha uma categoria (número): ");
        int opcao = lerInteiro();
        
        if (opcao >= 1 && opcao <= categorias.length) {
            CategoriaEvento categoria = categorias[opcao - 1];
            List<Evento> eventos = gerenciadorEventos.buscarPorCategoria(categoria);
            
            if (eventos.isEmpty()) {
                System.out.println("Nenhum evento encontrado para a categoria " + categoria.getDescricao());
            } else {
                System.out.println("\nEventos da categoria " + categoria.getDescricao() + ":");
                for (Evento evento : eventos) {
                    System.out.println(evento);
                }
            }
        } else {
            System.out.println("Opção inválida!");
        }
    }
    
    private void buscarPorNome() {
        System.out.println("\n=== BUSCAR POR NOME ===");
        System.out.print("Digite o nome do evento (busca parcial): ");
        String nome = scanner.nextLine();
        
        List<Evento> eventos = gerenciadorEventos.buscarPorNome(nome);
        
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado com o nome: " + nome);
        } else {
            System.out.println("\nEventos encontrados:");
            for (Evento evento : eventos) {
                System.out.println(evento);
            }
        }
    }

    private void participarEvento() {
        System.out.println("\n=== PARTICIPAR DE EVENTO ===");
        List<Evento> eventos = gerenciadorEventos.getEventosFuturos();
        
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento futuro disponível.");
            return;
        }
        
        System.out.println("Eventos disponíveis:");
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
        
        System.out.print("Digite o ID do evento que deseja participar: ");
        int id = lerInteiro();
        
        Evento evento = gerenciadorEventos.encontrarPorId(id);
        if (evento != null) {
            Usuario usuario = gerenciadorUsuarios.getUsuarioLogado();
            if (!evento.temParticipante(usuario)) {
                evento.adicionarParticipante(usuario);
                usuario.adicionarEvento(evento);
                System.out.println("Participação confirmada no evento: " + evento.getNome());
            } else {
                System.out.println("Você já está participando deste evento!");
            }
        } else {
            System.out.println("Evento não encontrado!");
        }
    }
    
    private void verMeusEventos() {
        System.out.println("\n=== MEUS EVENTOS ===");
        Usuario usuario = gerenciadorUsuarios.getUsuarioLogado();
        List<Evento> meusEventos = usuario.getEventosParticipando();
        
        if (meusEventos.isEmpty()) {
            System.out.println("Você não está participando de nenhum evento.");
            return;
        }
        
        for (Evento evento : meusEventos) {
            System.out.println(evento);
        }
    }
    
    private void cancelarParticipacao() {
        System.out.println("\n=== CANCELAR PARTICIPAÇÃO ===");
        Usuario usuario = gerenciadorUsuarios.getUsuarioLogado();
        List<Evento> meusEventos = usuario.getEventosParticipando();
        
        if (meusEventos.isEmpty()) {
            System.out.println("Você não está participando de nenhum evento.");
            return;
        }
        
        System.out.println("Seus eventos:");
        for (Evento evento : meusEventos) {
            System.out.println(evento);
        }
        
        System.out.print("Digite o ID do evento que deseja cancelar: ");
        int id = lerInteiro();
        
        Evento evento = gerenciadorEventos.encontrarPorId(id);
        if (evento != null && evento.temParticipante(usuario)) {
            evento.removerParticipante(usuario);
            usuario.removerEvento(evento);
            System.out.println("Participação cancelada no evento: " + evento.getNome());
        } else {
            System.out.println("Evento não encontrado ou você não está participando dele!");
        }
    }
    
    private void criarEvento() {
        System.out.println("\n=== CRIAR NOVO EVENTO ===");
        
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        System.out.println("Categorias disponíveis:");
        CategoriaEvento[] categorias = CategoriaEvento.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i].getDescricao());
        }
        
        System.out.print("Escolha uma categoria (número): ");
        int opcao = lerInteiro();
        
        if (opcao < 1 || opcao > categorias.length) {
            System.out.println("Categoria inválida!");
            return;
        }
        
        CategoriaEvento categoria = categorias[opcao - 1];
        
        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        LocalDateTime horario = lerDataHora();
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        Evento novoEvento = new Evento(nome, endereco, categoria, horario, descricao);
        gerenciadorEventos.adicionarEvento(novoEvento);
        
        System.out.println("Evento criado com sucesso! ID: " + novoEvento.getId());
    }
    private void verEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS ===");
        System.out.println(gerenciadorEventos.getEstatisticas());
    }
    
    private int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
    
    private LocalDate lerData() {
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine(), formatterData);
            } catch (DateTimeParseException e) {
                System.out.print("Digite uma data válida (dd/MM/yyyy): ");
            }
        }
    }
  
    private LocalDateTime lerDataHora() {
        while (true) {
            try {
                return LocalDateTime.parse(scanner.nextLine(), formatterDataHora);
            } catch (DateTimeParseException e) {
                System.out.print("Digite uma data e hora válidas (dd/MM/yyyy HH:mm): ");
            }
        }
    }

    public static void main(String[] args) {
        SistemaEventos sistema = new SistemaEventos();
        sistema.iniciar();
    }
}

