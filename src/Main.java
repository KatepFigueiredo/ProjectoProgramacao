import funcoes.GestaoUtilizadores;
import utilizadores.Administrador;
import utilizadores.Cliente;
import utilizadores.Tecnico;

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

    /**
     * Mostra o menu inicial ao utilizador.
     */
    private static void mostrarMenu() {
        System.out.println("\nMenu Inicial:");
        System.out.println("1 - Registar");
        System.out.println("2 - Autenticar");
        System.out.println("3 - Sair");
    }

    /**
     * Lê e retorna a opção escolhida pelo utilizador.
     *
     * @param mensagem Mensagem a ser exibida ao utilizador.
     * @return O número da opção escolhida.
     */
    private static int lerOpcao(String mensagem) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(mensagem);

        // Garantir que o input é um número inteiro
        while (!scanner.hasNextInt()) {
            System.out.println("Erro: Introduza um número válido.");
            scanner.next(); // Ignorar entrada inválida
        }
        return scanner.nextInt();
    }

    /**
     * Pergunta ao utilizador se deseja continuar e retorna a resposta.
     *
     * @param mensagem Mensagem a ser exibida ao utilizador.
     * @return true se o utilizador desejar continuar, false caso contrário.
     */
    private static boolean continuar(String mensagem) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(mensagem);

        // Verificar se a resposta é "s" ou "n" (case-insensitive)
        return scanner.nextLine().equalsIgnoreCase("s");
    }

    /**
     * Executa a ação correspondente à opção escolhida pelo utilizador.
     *
     * @param opcao A opção escolhida.
     */
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

    /**
     * Opção 1: Registar um novo utilizador.
     */
    private static void opcaoRegistar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Registo de Utilizador ===");
        System.out.println("Que tipo de utilizador deseja registar?");
        System.out.println("1 - Administrador");
        System.out.println("2 - Técnico");
        System.out.println("3 - Cliente");

        int tipo = lerOpcao("Escolha o tipo de utilizador:");

        // Coletar dados básicos
        System.out.print("Introduza o username: ");
        String username = scanner.nextLine();

        System.out.print("Introduza a password: ");
        String password = scanner.nextLine();

        System.out.print("Introduza o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Introduza o email: ");
        String email = scanner.nextLine();

        boolean estado = continuar("O utilizador está ativo? [S/N]");

        // Dados específicos para Técnico e Cliente
        String nif = null;
        String morada = null;
        String contacto = null;

        if (tipo == 2 || tipo == 3) {
            System.out.print("Introduza o NIF: ");
            nif = scanner.nextLine();

            System.out.print("Introduza a morada: ");
            morada = scanner.nextLine();

            System.out.print("Introduza o contacto telefónico: ");
            contacto = scanner.nextLine();
        }

        // Criar o utilizador e adicionar à lista
        switch (tipo) {
            case 1:
                Administrador admin = new Administrador(username, password, nome, estado, email);
                if (gestaoUtilizadores.adicionarUtilizador(admin)) {
                    System.out.println("Administrador registado com sucesso!");
                }
                break;
            case 2:
                Tecnico tecnico = new Tecnico(username, password, nome, estado, email, nif, morada, contacto);
                if (gestaoUtilizadores.adicionarUtilizador(tecnico)) {
                    System.out.println("Técnico registado com sucesso!");
                }
                break;
            case 3:
                Cliente cliente = new Cliente(username, password, nome, estado, email, nif, morada, contacto);
                if (gestaoUtilizadores.adicionarUtilizador(cliente)) {
                    System.out.println("Cliente registado com sucesso!");
                }
                break;
            default:
                System.out.println("Opção inválida! Tipo de utilizador desconhecido.");
        }
    }

    /**
     * Opção 2: Autenticar um utilizador.
     */
    private static void opcaoAutenticar() {
        System.out.println("\n=== Autenticação de Utilizador ===");
        // Lógica de autenticação será implementada aqui
        // Verificar credenciais no ficheiro credenciais_acesso.txt
    }

    /**
     * Opção 3: Encerrar a aplicação.
     */
    private static void opcaoSair() {
        System.out.println("A encerrar aplicação. Adeus.");
        System.exit(0); // Finaliza o programa
    }

    /**
     * Exibe uma mensagem de erro para opções inválidas.
     */
    private static void mostrarErro() {
        System.out.println("Opção inválida! Por favor, escolha uma das opções disponíveis.");
    }
}
