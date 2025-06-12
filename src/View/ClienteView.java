package src.View;

import src.Controller.ClienteController;
import src.Interface.Gerenciavel;
import src.Model.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private Gerenciavel<Cliente> controller;
    private Scanner scanner;

    public ClienteView(Gerenciavel<Cliente> controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR CLIENTES ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Buscar Cliente");
            System.out.println("3. Listar Todos os Clientes");
            System.out.println("4. Atualizar Cliente");
            System.out.println("5. Remover Cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    buscarCliente();
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    atualizarCliente();
                    break;
                case 5:
                    removerCliente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        System.out.println("\n=== CADASTRAR CLIENTE ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Renda Mensal: ");
        double rendaMensal = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer

        Cliente cliente = new Cliente(nome, cpf, telefone, email, endereco, rendaMensal);
        if (controller.cadastrar(cliente)) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro: Cliente com este CPF já existe!");
        }
    }

    private void buscarCliente() {
        System.out.println("\n=== BUSCAR CLIENTE ===");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = controller.buscarPorId(cpf);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private void listarClientes() {
        System.out.println("\n=== LISTA DE CLIENTES ===");
        List<Cliente> clientes = controller.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
                System.out.println("---");
            }
        }
    }

    private void atualizarCliente() {
        System.out.println("\n=== ATUALIZAR CLIENTE ===");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = controller.buscarPorId(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.println("Cliente atual: " + cliente);
        System.out.print("Novo nome (atual: " + cliente.getNome() + "): ");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) nome = cliente.getNome();

        System.out.print("Novo telefone (atual: " + cliente.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (telefone.isEmpty()) telefone = cliente.getTelefone();

        System.out.print("Novo email (atual: " + cliente.getEmail() + "): ");
        String email = scanner.nextLine();
        if (email.isEmpty()) email = cliente.getEmail();

        System.out.print("Novo endereço (atual: " + cliente.getEndereco() + "): ");
        String endereco = scanner.nextLine();
        if (endereco.isEmpty()) endereco = cliente.getEndereco();

        System.out.print("Nova renda mensal (atual: " + cliente.getRendaMensal() + "): ");
        String rendaStr = scanner.nextLine();
        double rendaMensal = rendaStr.isEmpty() ? cliente.getRendaMensal() : Double.parseDouble(rendaStr);

        if (controller.atualizar(cpf, new Cliente(nome, cpf, telefone, email, endereco, rendaMensal))) {
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar cliente!");
        }
    }

    private void removerCliente() {
        System.out.println("\n=== REMOVER CLIENTE ===");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        if (controller.remover(cpf)) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }
}
