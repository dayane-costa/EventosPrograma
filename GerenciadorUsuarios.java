import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;
    private static final String ARQUIVO_USUARIOS = "usuarios.data";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        carregarUsuarios();
    }

    public boolean registrarUsuario(Usuario usuario) {
        if (buscarPorEmail(usuario.getEmail()) != null) {
            return false; 
        }
        
        usuarios.add(usuario);
        salvarUsuarios();
        return true;
    }
    
    public boolean fazerLogin(String email) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario != null) {
            this.usuarioLogado = usuario;
            return true;
        }
        return false;
    }

    public void fazerLogout() {
        this.usuarioLogado = null;
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean isUsuarioLogado() {
        return usuarioLogado != null;
    }

    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> getTodosUsuarios() {
        return new ArrayList<>(usuarios);
    }

    private void salvarUsuarios() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_USUARIOS))) {
            for (Usuario usuario : usuarios) {
                writer.println(usuarioToFileString(usuario));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    private void carregarUsuarios() {
        File arquivo = new File(ARQUIVO_USUARIOS);
        if (!arquivo.exists()) {
            return; // Arquivo não existe ainda, lista vazia
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    Usuario usuario = parseUsuarioFromFile(linha);
                    if (usuario != null) {
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    private String usuarioToFileString(Usuario usuario) {
        return String.format("%s|%s|%s|%s|%s",
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getDataNascimento().format(FORMATTER),
                usuario.getEndereco());
    }
    
    private Usuario parseUsuarioFromFile(String linha) {
        try {
            String[] partes = linha.split("\\|");
            if (partes.length != 5) {
                System.err.println("Formato inválido na linha: " + linha);
                return null;
            }
            
            String nome = partes[0];
            String email = partes[1];
            String telefone = partes[2];
            LocalDate dataNascimento = LocalDate.parse(partes[3], FORMATTER);
            String endereco = partes[4];
            
            return new Usuario(nome, email, telefone, dataNascimento, endereco);
        } catch (Exception e) {
            System.err.println("Erro ao processar linha: " + linha + " - " + e.getMessage());
            return null;
        }
    }
}

