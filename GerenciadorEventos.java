import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorEventos {
    private List<Evento> eventos;
    private static final String ARQUIVO_EVENTOS = "events.data";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public GerenciadorEventos() {
        this.eventos = new ArrayList<>();
        carregarEventos();
    }
    
    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
    }
    
    public void removerEvento(Evento evento) {
        eventos.remove(evento);
        salvarEventos();
    }
    
    public List<Evento> getTodosEventos() {
        return new ArrayList<>(eventos);
    }
    
    public List<Evento> getEventosOrdenadosPorProximidade() {
        return eventos.stream()
                .sorted(Comparator.comparing(Evento::getHorario))
                .collect(Collectors.toList());
    }
    
    public List<Evento> getEventosFuturos() {
        return eventos.stream()
                .filter(Evento::vaiAcontecer)
                .sorted(Comparator.comparing(Evento::getHorario))
                .collect(Collectors.toList());
    }
    
    public List<Evento> getEventosAcontecendo() {
        return eventos.stream()
                .filter(Evento::estaAcontecendo)
                .collect(Collectors.toList());
    }

    public List<Evento> getEventosPassados() {
        return eventos.stream()
                .filter(Evento::jaOcorreu)
                .sorted(Comparator.comparing(Evento::getHorario).reversed())
                .collect(Collectors.toList());
    }
    
    public List<Evento> buscarPorCategoria(CategoriaEvento categoria) {
        return eventos.stream()
                .filter(e -> e.getCategoria() == categoria)
                .sorted(Comparator.comparing(Evento::getHorario))
                .collect(Collectors.toList());
    }

    public List<Evento> buscarPorNome(String nome) {
        return eventos.stream()
                .filter(e -> e.getNome().toLowerCase().contains(nome.toLowerCase()))
                .sorted(Comparator.comparing(Evento::getHorario))
                .collect(Collectors.toList());
    }
    
    public Evento encontrarPorId(int id) {
        return eventos.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    private void salvarEventos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_EVENTOS))) {
            for (Evento evento : eventos) {
                writer.println(evento.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    private void carregarEventos() {
        File arquivo = new File(ARQUIVO_EVENTOS);
        if (!arquivo.exists()) {
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    Evento evento = parseEventoFromFile(linha);
                    if (evento != null) {
                        eventos.add(evento);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar eventos: " + e.getMessage());
        }
    }

    private Evento parseEventoFromFile(String linha) {
        try {
            String[] partes = linha.split("\\|");
            if (partes.length != 6) {
                System.err.println("Formato inválido na linha: " + linha);
                return null;
            }
            
            int id = Integer.parseInt(partes[0]);
            String nome = partes[1];
            String endereco = partes[2];
            CategoriaEvento categoria = CategoriaEvento.valueOf(partes[3]);
            LocalDateTime horario = LocalDateTime.parse(partes[4], FORMATTER);
            String descricao = partes[5];
            
            return new Evento(id, nome, endereco, categoria, horario, descricao);
        } catch (Exception e) {
            System.err.println("Erro ao processar linha: " + linha + " - " + e.getMessage());
            return null;
        }
    }
    
    public String getEstatisticas() {
        long total = eventos.size();
        long futuros = eventos.stream().filter(Evento::vaiAcontecer).count();
        long acontecendo = eventos.stream().filter(Evento::estaAcontecendo).count();
        long passados = eventos.stream().filter(Evento::jaOcorreu).count();
        
        return String.format("Total de eventos: %d | Futuros: %d | Acontecendo: %d | Passados: %d", 
                           total, futuros, acontecendo, passados);
    }
}

