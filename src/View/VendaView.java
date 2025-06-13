package src.View;

import src.Controller.ClienteController;
import src.Controller.VeiculoController;
import src.Controller.VendaController;
import src.Model.Cliente;
import src.Model.Veiculo;
import src.Model.Venda;
import src.Model.Vendedor;

import java.util.List;
import java.util.Scanner;

public class VendaView {
    private VendaController vendaController;
    private ClienteController clienteController;
    private VeiculoController veiculoController;
    private Scanner scanner;

    public VendaView(VendaController vendaController, ClienteController clienteController, VeiculoController veiculoController) {
        this.vendaController = vendaController;
        this.clienteController = clienteController;
        this.veiculoController = veiculoController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR VENDAS ===");
            System.out.println("1. Realizar Venda");
            System.out.println("2. Buscar Venda por ID");
            System.out.println("3. Listar Todas as Vendas");
            System.out.println("4. Vendas por Cliente");
            System.out.println("5. Relatório de Faturamento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 1:
                    realizarVenda();
                    break;
                case 2:
                    buscarVenda();
                    break;
                case 3:
                    listarVendas();
                    break;
                case 4:
                    vendasPorCliente();
                    break;
                case 5:
                    relatorioFaturamento();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void realizarVenda() {
        System.out.println("\n=== REALIZAR VENDA ===");

        System.out.print("CPF do cliente: ");
        String cpfCliente = scanner.nextLine();
        Cliente cliente = clienteController.buscarPorId(cpfCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.print("Chassi do veículo: ");
        String chassi = scanner.nextLine();
        Veiculo veiculo = veiculoController.buscarPorChassi(chassi);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado!");
            return;
        }

        if (!veiculo.isDisponivel()) {
            System.out.println("Veículo não está disponível!");
            return;
        }

        // Vendedor pra Testar
        Vendedor vendedor = new Vendedor("João", "123.456.789-00", "(41) 99999-9999",
                "joao@email.com", "V001", 3000.0, 5.0);

        System.out.print("Forma de pagamento: ");
        String formaPagamento = scanner.nextLine();

        String idVenda = vendaController.realizarVenda(cliente, vendedor, veiculo, formaPagamento);
        if (idVenda != null) {
            System.out.println("Venda realizada com sucesso!");
            System.out.println("ID da venda: " + idVenda);
            System.out.println("Valor: R$ " + veiculo.getPreco());
        } else {
            System.out.println("Erro ao realizar venda!");
        }
    }

    private void buscarVenda() {
        System.out.println("\n=== BUSCAR VENDA ===");
        System.out.print("ID da venda: ");
        String id = scanner.nextLine();

        Venda venda = vendaController.buscarPorId(id);
        if (venda != null) {
            System.out.println("Venda encontrada:");
            System.out.println(venda);
        } else {
            System.out.println("Venda não encontrada!");
        }
    }

    private void listarVendas() {
        System.out.println("\n=== TODAS AS VENDAS ===");
        List<Venda> vendas = vendaController.listarTodas();

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
                System.out.println("---");
            }
        }
    }

    private void vendasPorCliente() {
        System.out.println("\n=== VENDAS POR CLIENTE ===");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteController.buscarPorId(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        List<Venda> vendas = vendaController.buscarPorCliente(cliente);
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda encontrada para este cliente.");
        } else {
            System.out.println("Vendas do cliente " + cliente.getNome() + ":");
            for (Venda venda : vendas) {
                System.out.println(venda);
                System.out.println("---");
            }
        }
    }

    private void relatorioFaturamento() {
        System.out.println("\n=== RELATÓRIO DE FATURAMENTO ===");
        double faturamentoTotal = vendaController.calcularFaturamentoTotal();
        int totalVendas = vendaController.listarTodas().size();

        System.out.println("Total de vendas: " + totalVendas);
        System.out.println("Faturamento total: R$ " + String.format("%.2f", faturamentoTotal));
        if (totalVendas > 0) {
            System.out.println("Ticket médio: R$ " + String.format("%.2f", faturamentoTotal / totalVendas));
        }
    }
}
