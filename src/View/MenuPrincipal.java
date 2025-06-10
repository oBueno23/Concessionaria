package src.View;

import src.Controller.*;

import java.util.Scanner;

public class MenuPrincipal {
    private ClienteController clienteController;
    private VeiculoController veiculoController;
    private VendaController vendaController;
    private TestDriveController testDriveController;
    private FinanceiroController financeiroController;

    private ClienteView clienteView;
    private VeiculoView veiculoView;
    private VendaView vendaView;
    private TestDriveView testDriveView;
    private FinanceiroView financeiroView;

    private Scanner scanner;

    public MenuPrincipal(){
        this.clienteController = new ClienteController();
        this.veiculoController = new VeiculoController();
        this.vendaController = new VendaController();
        this.testDriveController = new TestDriveController();
        this.financeiroController = new FinanceiroController();

        this.clienteView = new ClienteView(clienteController);
        this.veiculoView = new VeiculoView(veiculoController);
        this.vendaView = new VendaView(vendaController, clienteController, veiculoController);
        this.testDriveView = new TestDriveView(testDriveController, clienteController, veiculoController);
        this.financeiroView = new FinanceiroView(financeiroController, vendaController);

        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE CONCESSIONÁRIA ===");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Veículos");
            System.out.println("3. Gerenciar Vendas");
            System.out.println("4. Gerenciar Test Drives");
            System.out.println("5. Gerenciar Financeiro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    clienteView.exibirMenu();
                    break;
                case 2:
                    veiculoView.exibirMenu();
                    break;
                case 3:
                    vendaView.exibirMenu();
                    break;
                case 4:
                    testDriveView.exibirMenu();
                    break;
                case 5:
                    financeiroView.exibirMenu();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.exibirMenu();
    }
}

