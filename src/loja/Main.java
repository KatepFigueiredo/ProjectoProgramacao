package loja;
import funcoes.GestaoUtilizadores;
import utilizadores.Utilizador;

import java.util.Scanner;

public class Main {

    // Instância de GestaoUtilizadores para gerir a lista de utilizadores
    private static GestaoUtilizadores gestaoUtilizadores = new GestaoUtilizadores();

    public static void main(String[] args) {
        // Verificar se é necessário registar um administrador
        if (gestaoUtilizadores.verificarFicheiroCredenciais()) {
            gestaoUtilizadores.registarAdministradorInicial();
        }

        // Carregar dados ao iniciar a aplicação
        gestaoUtilizadores.carregarDados();

        // Loop principal do programa
        do {
            // Mostrar o menu inicial
            mostrarMenu();

            // Pedir ao utilizador a opção desejada
            int opcao = lerOpcao("Por favor introduza uma opção:");

            // Executar a opção escolhida
            executarOpcao(opcao);
        } while (continuar("Deseja continuar? [S/N]"));

        // Guardar os dados antes de encerrar a aplicação
        gestaoUtilizadores.guardarDados();
    }

    private static void mostrarMenu() {
        System.out.println("\nMenu Inicial:");
        System.out.println("1 - Registar");
        System.out.println("2 - Autenticar");
        System.out.println("3 - Sair");
    }

    private static int lerOpcao(String mensagem) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(mensagem);

        while (!scanner.hasNextInt()) {
            System.out.println("Erro: Introduza um número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static boolean continuar(String mensagem) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(mensagem);
        return scanner.nextLine().equalsIgnoreCase("s");
    }

    private static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                opcaoRegistar();
                break;
            case 2:
                opcaoAutenticar();
                break;
            case 3:
                opcaoSair();
                break;
            default:
                mostrarErro();
        }
    }

    private static void opcaoRegistar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Registo de Utilizador ===");
        System.out.println("Que tipo de utilizador deseja registar?");
        System.out.println("1 - Administrador");
        System.out.println("2 - Técnico");
        System.out.println("3 - Cliente");

        int tipo = lerOpcao("Escolha o tipo de utilizador:");

        System.out.print("Introduza o username: ");
        String username = scanner.nextLine();

        System.out.print("Introduza a password: ");
        String password = scanner.nextLine();

        System.out.print("Introduza o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Introduza o email: ");
        String email = scanner.nextLine();

        boolean estado = continuar("O utilizador está ativo? [S/N]");

        String nif = null, morada = null, contacto = null;

        if (tipo == 2 || tipo == 3) {
            System.out.print("Introduza o NIF: ");
            nif = scanner.nextLine();

            System.out.print("Introduza a morada: ");
            morada = scanner.nextLine();

            System.out.print("Introduza o contacto telefónico: ");
            contacto = scanner.nextLine();
        }

        Utilizador novoUtilizador = switch (tipo) {
            case 1 -> new Utilizador(username, password, nome, estado, email, "Administrador");
            case 2 -> new Utilizador(username, password, nome, estado, email, "Tecnico");
            case 3 -> new Utilizador(username, password, nome, estado, email, "Cliente");
            default -> null;
        };

        if (novoUtilizador != null && gestaoUtilizadores.adicionarUtilizador(novoUtilizador)) {
            System.out.println("Utilizador registado com sucesso!");
        } else {
            System.out.println("Erro ao registar o utilizador.");
        }
    }

    private static void opcaoAutenticar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Autenticação de Utilizador ===");
        System.out.print("Digite o username: ");
        String username = scanner.nextLine();

        System.out.print("Digite a password: ");
        String password = scanner.nextLine();

        Utilizador utilizador = gestaoUtilizadores.autenticarUtilizador(username, password);

        if (utilizador != null) {
            System.out.println("Bem-vindo, " + utilizador.getNome() + "!");
            System.out.println("Abrindo menu para " + utilizador.getTipo() + "...");
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
        }
    }

    private static void opcaoSair() {
        System.out.println("A encerrar aplicação. Adeus.");
        System.exit(0);
    }

    private static void mostrarErro() {
        System.out.println("Opção inválida! Por favor, escolha uma das opções disponíveis.");
    }
}
