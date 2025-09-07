import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private String endereco;
    private CategoriaEvento categoria;
    private LocalDateTime horario;
    private String descricao;
    private List<Usuario> participantes;
    private int id;
    private static int proximoId = 1;
    
    public Evento(String nome, String endereco, CategoriaEvento categoria, 
                  LocalDateTime horario, String descricao) {
        this.id = proximoId++;
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }
    
    public Evento(int id, String nome, String endereco, CategoriaEvento categoria, 
                  LocalDateTime horario, String descricao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
        
        if (id >= proximoId) {
            proximoId = id + 1;
        }
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public CategoriaEvento getCategoria() {
        return categoria;
    }
    
    public void setCategoria(CategoriaEvento categoria) {
        this.categoria = categoria;
    }
    
    public LocalDateTime getHorario() {
        return horario;
    }
    
    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public List<Usuario> getParticipantes() {
        return participantes;
    }
    
    public void adicionarParticipante(Usuario usuario) {
        if (!participantes.contains(usuario)) {
            participantes.add(usuario);
        }
    }
    
    public void removerParticipante(Usuario usuario) {
        participantes.remove(usuario);
    }
    
    public boolean temParticipante(Usuario usuario) {
        return participantes.contains(usuario);
    }
    
    public int getNumeroParticipantes() {
        return participantes.size();
    }
    
    public boolean jaOcorreu() {
        return LocalDateTime.now().isAfter(horario);
    }
    
    public boolean estaAcontecendo() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime fimEvento = horario.plusHours(3); // Assumindo que eventos duram 3 horas
        return agora.isAfter(horario) && agora.isBefore(fimEvento);
    }
    
    public boolean vaiAcontecer() {
        return LocalDateTime.now().isBefore(horario);
    }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return id + "|" + nome + "|" + endereco + "|" + categoria.name() + 
               "|" + horario.format(formatter) + "|" + descricao;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String status = "";
        if (jaOcorreu()) {
            status = " (Já ocorreu)";
        } else if (estaAcontecendo()) {
            status = " (Acontecendo agora!)";
        }
        
        return String.format("ID: %d | %s | %s | %s | %s | %d participantes%s",
                id, nome, categoria.getDescricao(), 
                horario.format(formatter), endereco, getNumeroParticipantes(), status);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Evento evento = (Evento) obj;
        return id == evento.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

