package src.View;

import src.Controller.VeiculoController;
import src.Model.Veiculo;

import java.util.List;
import java.util.Scanner;

public class VeiculoView {
    private VeiculoController controller;
    private Scanner scanner;

    public VeiculoView(VeiculoController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR VEÍCULOS ===");
            System.out.println("1. Cadastrar Carro");
            System.out.println("2. Cadastrar Moto");
            System.out.println("3. Buscar Veículo por Chassi");
            System.out.println("4. Buscar Veículos por Marca");
            System.out.println("5. Listar Veículos Disponíveis");
            System.out.println("6. Listar Todos os Veículos");
            System.out.println("7. Remover Veículo");
            System.out.println("8. Status do Estoque");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 1:
                    cadastrarCarro();
                    break;
                case 2:
                    cadastrarMoto();
                    break;
                case 3:
                    buscarPorChassi();
                    break;
                case 4:
                    buscarPorMarca();
                    break;
                case 5:
                    listarDisponiveis();
                    break;
                case 6:
                    listarTodos();
                    break;
                case 7:
                    removerVeiculo();
                    break;
                case 8:
                    statusEstoque();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarCarro() {
        System.out.println("\n=== CADASTRAR CARRO ===");
        System.out.print("Chassi: ");
        String chassi = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Número de portas: ");
        int numeroPortas = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tipo de combustível: ");
        String tipoCombustivel = scanner.nextLine();
        System.out.print("Tem ar condicionado? (true/false): ");
        boolean arCondicionado = scanner.nextBoolean();
        scanner.nextLine();

        if (controller.cadastrarCarro(chassi, marca, modelo, ano, cor, preco, numeroPortas, tipoCombustivel, arCondicionado)) {
            System.out.println("Carro cadastrado com sucesso!");
        } else {
            System.out.println("Erro: Veículo com este chassi já existe!");
        }
    }

    private void cadastrarMoto() {
        System.out.println("\n=== CADASTRAR MOTO ===");
        System.out.print("Chassi: ");
        String chassi = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Cilindrada: ");
        int cilindrada = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tipo de partida: ");
        String tipoPartida = scanner.nextLine();
        System.out.print("Tem bagageiro? (true/false): ");
        boolean bagageiro = scanner.nextBoolean();
        scanner.nextLine();

        if (controller.cadastrarMoto(chassi, marca, modelo, ano, cor, preco, cilindrada, tipoPartida, bagageiro)) {
            System.out.println("Moto cadastrada com sucesso!");
        } else {
            System.out.println("Erro: Veículo com este chassi já existe!");
        }
    }

    private void buscarPorChassi() {
        System.out.println("\n=== BUSCAR VEÍCULO ===");
        System.out.print("Chassi: ");
        String chassi = scanner.nextLine();

        Veiculo veiculo = controller.buscarPorChassi(chassi);
        if (veiculo != null) {
            System.out.println("Veículo encontrado:");
            System.out.println(veiculo);
        } else {
            System.out.println("Veículo não encontrado!");
        }
    }

    private void buscarPorMarca() {
        System.out.println("\n=== BUSCAR POR MARCA ===");
        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        List<Veiculo> veiculos = controller.buscarPorMarca(marca);
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo encontrado para esta marca.");
        } else {
            System.out.println("Veículos encontrados:");
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo);
                System.out.println("---");
            }
        }
    }

    private void listarDisponiveis() {
        System.out.println("\n=== VEÍCULOS DISPONÍVEIS ===");
        List<Veiculo> veiculos = controller.listarDisponiveis();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo disponível.");
        } else {
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo);
                System.out.println("---");
            }
        }
    }

    private void listarTodos() {
        System.out.println("\n=== TODOS OS VEÍCULOS ===");
        List<Veiculo> veiculos = controller.listarTodos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo + " - " + (veiculo.isDisponivel() ? "DISPONÍVEL" : "VENDIDO"));
                System.out.println("---");
            }
        }
    }

    private void removerVeiculo() {
        System.out.println("\n=== REMOVER VEÍCULO ===");
        System.out.print("Chassi do veículo: ");
        String chassi = scanner.nextLine();

        if (controller.removerVeiculo(chassi)) {
            System.out.println("Veículo removido com sucesso!");
        } else {
            System.out.println("Veículo não encontrado!");
        }
    }

    private void statusEstoque() {
        System.out.println("\n=== STATUS DO ESTOQUE ===");
        System.out.println("Total de veículos: " + controller.getEstoque().quantidadeTotal());
        System.out.println("Veículos disponíveis: " + controller.getEstoque().quantidadeDisponiveis());
        System.out.println("Veículos vendidos: " + (controller.getEstoque().quantidadeTotal() - controller.getEstoque().quantidadeDisponiveis()));
    }
}
