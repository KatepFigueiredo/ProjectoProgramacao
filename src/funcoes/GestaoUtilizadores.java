package funcoes;

import utilizadores.Utilizador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestaoUtilizadores implements Serializable {
    private List<Utilizador> utilizadores;
    private final String ficheiroCredenciais = "credenciais_acesso.txt";
    private final String ficheiroDados = "dados_apl.dat";

    public GestaoUtilizadores() {
        this.utilizadores = new ArrayList<>();
    }

    public boolean adicionarUtilizador(Utilizador utilizador) {
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equalsIgnoreCase(utilizador.getUsername())
                || u.getEmail().equalsIgnoreCase(utilizador.getEmail())) {
                System.out.println("Erro: Username ou email já em uso.");
                return false;
            }
        }
        utilizadores.add(utilizador);
        guardarCredenciais(utilizador);
        return true;
    }

    public Utilizador autenticarUtilizador(String username, String password) {
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    private void guardarCredenciais(Utilizador utilizador) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheiroCredenciais, true))) {
            writer.write(utilizador.getUsername() + ";" + utilizador.getPassword());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao guardar credenciais: " + e.getMessage());
        }
    }

    public void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheiroDados))) {
            utilizadores = (List<Utilizador>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    public void guardarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheiroDados))) {
            oos.writeObject(utilizadores);
        } catch (IOException e) {
            System.out.println("Erro ao guardar dados: " + e.getMessage());
        }
    }

    public boolean verificarFicheiroCredenciais() {
        return !new File(ficheiroCredenciais).exists();
    }

    public void registarAdministradorInicial() {
        Utilizador admin = new Utilizador("admin", "admin123", "Administrador Inicial", true, "admin@example.com", "Administrador");
        adicionarUtilizador(admin);
        guardarDados();
    }
}
