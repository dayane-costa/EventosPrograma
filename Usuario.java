import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String endereco;
    private List<Evento> eventosParticipando;
    
    public Usuario(String nome, String email, String telefone, LocalDate dataNascimento, String endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.eventosParticipando = new ArrayList<>();
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public List<Evento> getEventosParticipando() {
        return eventosParticipando;
    }
    
    public void adicionarEvento(Evento evento) {
        if (!eventosParticipando.contains(evento)) {
            eventosParticipando.add(evento);
        }
    }
    
    public void removerEvento(Evento evento) {
        eventosParticipando.remove(evento);
    }
    
    public boolean estaParticipando(Evento evento) {
        return eventosParticipando.contains(evento);
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}

