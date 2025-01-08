package funcoes;

import utilizadores.Administrador;

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

    // Adicionar utilizador ao sistema
    public boolean adicionarUtilizador(Utilizador utilizador) {
        if (verificarUnicidadeUsernameEmail(utilizador.getUsername(), utilizador.getEmail())) {
            if (utilizador instanceof utilizadores.Cliente || utilizador instanceof utilizadores.Tecnico) {
                String nif = null;
                String contacto = null;

                if (utilizador instanceof utilizadores.Cliente cliente) {
                    nif = cliente.getNif();
                    contacto = cliente.getContacto();
                } else if (utilizador instanceof utilizadores.Tecnico tecnico) {
                    nif = tecnico.getNif();
                    contacto = tecnico.getContacto();
                }

                if (!verificarUnicidadeNifContacto(nif, contacto)) {
                    System.out.println("Erro: NIF ou contacto duplicado.");
                    return false;
                }
            }

            // Adiciona o utilizador e atualiza ficheiro de credenciais
            utilizadores.add(utilizador);
            guardarCredenciais(utilizador);
            System.out.println("Utilizador registado com sucesso: " + utilizador.getNome());
            return true;
        }

        System.out.println("Erro: Username ou email duplicado.");
        return false;
    }

    // Verificar unicidade de username e email
    private boolean verificarUnicidadeUsernameEmail(String username, String email) {
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equalsIgnoreCase(username) || u.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    // Verificar unicidade de NIF e contacto
    private boolean verificarUnicidadeNifContacto(String nif, String contacto) {
        for (Utilizador u : utilizadores) {
            if (u instanceof utilizadores.Cliente cliente) {
                if (cliente.getNif().equals(nif) || cliente.getContacto().equals(contacto)) {
                    return false;
                }
            } else if (u instanceof utilizadores.Tecnico tecnico) {
                if (tecnico.getNif().equals(nif) || tecnico.getContacto().equals(contacto)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Guardar credenciais em ficheiro de texto
    private void guardarCredenciais(Utilizador utilizador) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheiroCredenciais, true))) {
            writer.write(utilizador.getUsername() + ";" + utilizador.getPassword());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao guardar credenciais: " + e.getMessage());
        }
    }

    // Guardar todos os dados no ficheiro binário
    public void guardarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheiroDados))) {
            oos.writeObject(utilizadores);
            System.out.println("Dados guardados com sucesso no ficheiro: " + ficheiroDados);
        } catch (IOException e) {
            System.out.println("Erro ao guardar dados: " + e.getMessage());
        }
    }

    // Carregar dados do ficheiro binário
    public void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheiroDados))) {
            utilizadores = (List<Utilizador>) ois.readObject();
            System.out.println("Dados carregados com sucesso do ficheiro: " + ficheiroDados);
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de dados não encontrado. Nenhum dado foi carregado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    // Verificar se o ficheiro de credenciais existe e está vazio
    public boolean verificarFicheiroCredenciais() {
        File ficheiro = new File(ficheiroCredenciais);
        if (!ficheiro.exists()) {
            System.out.println("Ficheiro de credenciais não encontrado. Será necessário registar um administrador.");
            return true;
        }

        // Verificar se o ficheiro está vazio
        try (BufferedReader reader = new BufferedReader(new FileReader(ficheiro))) {
            if (reader.readLine() == null) {
                System.out.println("Ficheiro de credenciais vazio. Será necessário registar um administrador.");
                return true;
            }
        } catch (IOException e) {
            System.out.println("Erro ao verificar o ficheiro de credenciais: " + e.getMessage());
        }

        return false;
    }

    // Forçar o registo de um administrador
    public void registarAdministradorInicial() {
        System.out.println("\n=== Registo Obrigatório de Administrador ===");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Introduza o username: ");
            String username = reader.readLine();

            System.out.print("Introduza a password: ");
            String password = reader.readLine();

            System.out.print("Introduza o nome: ");
            String nome = reader.readLine();

            System.out.print("Introduza o email: ");
            String email = reader.readLine();

            boolean estado = true; // Administrador inicial estará sempre ativo

            // Criar o administrador
            Administrador admin = new Administrador(username, password, nome, estado, email);

            // Adicionar o administrador e guardar os dados
            if (adicionarUtilizador(admin)) {
                guardarDados();
                System.out.println("Administrador registado com sucesso.");
            } else {
                System.out.println("Erro ao registar o administrador. Tente novamente.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler os dados do administrador: " + e.getMessage());
        }
    }

    // Listar todos os utilizadores (apenas para debug ou relatórios)
    public void listarUtilizadores() {
        System.out.println("\nLista de Utilizadores:");
        for (Utilizador u : utilizadores) {
            System.out.println(u);
        }
    }
}
