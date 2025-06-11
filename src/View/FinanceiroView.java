package src.View;

import src.Controller.FinanceiroController;
import src.Controller.VendaController;
import src.Model.Financiamento;
import src.Model.Venda;

import java.util.List;
import java.util.Scanner;

public class FinanceiroView {
    private FinanceiroController financeiroController;
    private VendaController vendaController;
    private Scanner scanner;

    public FinanceiroView(FinanceiroController financeiroController, VendaController vendaController) {
        this.financeiroController = financeiroController;
        this.vendaController = vendaController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR FINANCEIRO ===");
            System.out.println("1. Solicitar Financiamento");
            System.out.println("2. Aprovar Financiamento");
            System.out.println("3. Reprovar Financiamento");
            System.out.println("4. Buscar Financiamento por ID");
            System.out.println("5. Listar Todos os Financiamentos");
            System.out.println("6. Financiamentos por Status");
            System.out.println("7. Relatório Financeiro");
            System.out.println("8. Simular Financiamento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    solicitarFinanciamento();
                    break;
                case 2:
                    aprovarFinanciamento();
                    break;
                case 3:
                    reprovarFinanciamento();
                    break;
                case 4:
                    buscarFinanciamento();
                    break;
                case 5:
                    listarTodosFinanciamentos();
                    break;
                case 6:
                    financiamentosPorStatus();
                    break;
                case 7:
                    relatorioFinanceiro();
                    break;
                case 8:
                    simularFinanciamento();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void solicitarFinanciamento() {
        System.out.println("\n=== SOLICITAR FINANCIAMENTO ===");

        System.out.print("ID da venda: ");
        String idVenda = scanner.nextLine();

        Venda venda = vendaController.buscarPorId(idVenda);
        if (venda == null) {
            System.out.println("Venda não encontrada!");
            return;
        }

        if (venda.getFinanciamento() != null) {
            System.out.println("Esta venda já possui um financiamento associado!");
            return;
        }

        System.out.println("Venda encontrada: " + venda);
        System.out.println("Valor do veículo: R$ " + venda.getValor());

        System.out.print("Valor a ser financiado: R$ ");
        double valorFinanciado = scanner.nextDouble();

        if (valorFinanciado > venda.getValor()) {
            System.out.println("Valor financiado não pode ser maior que o valor da venda!");
            scanner.nextLine(); // Limpar buffer
            return;
        }

        System.out.print("Número de parcelas: ");
        int numeroParcelas = scanner.nextInt();

        if (numeroParcelas < 1 || numeroParcelas > 96) {
            System.out.println("Número de parcelas deve estar entre 1 e 96!");
            scanner.nextLine(); // Limpar buffer
            return;
        }

        System.out.print("Taxa de juros mensal (%): ");
        double taxaJuros = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer

        System.out.print("Banco: ");
        String banco = scanner.nextLine();

        String idFinanciamento = financeiroController.solicitarFinanciamento(
                venda, valorFinanciado, numeroParcelas, taxaJuros, banco);

        if (idFinanciamento != null) {
            System.out.println("Financiamento solicitado com sucesso!");
            System.out.println("ID do Financiamento: " + idFinanciamento);

            // Mostrar detalhes da simulação
            Financiamento financiamento = financeiroController.buscarPorId(idFinanciamento);
            System.out.println("Valor das parcelas: R$ " + String.format("%.2f", financiamento.getValorParcela()));
            System.out.println("Total a pagar: R$ " + String.format("%.2f", financiamento.getValorParcela() * numeroParcelas));
        } else {
            System.out.println("Erro ao solicitar financiamento!");
        }
    }

    private void aprovarFinanciamento() {
        System.out.println("\n=== APROVAR FINANCIAMENTO ===");
        System.out.print("ID do Financiamento: ");
        String id = scanner.nextLine();

        Financiamento financiamento = financeiroController.buscarPorId(id);
        if (financiamento == null) {
            System.out.println("Financiamento não encontrado!");
            return;
        }

        if (!financiamento.getStatus().equals("PENDENTE")) {
            System.out.println("Apenas financiamentos pendentes podem ser aprovados!");
            System.out.println("Status atual: " + financiamento.getStatus());
            return;
        }

        System.out.println("Financiamento encontrado:");
        System.out.println(financiamento);
        System.out.print("Confirma a aprovação? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s") || confirmacao.equalsIgnoreCase("sim")) {
            if (financeiroController.aprovarFinanciamento(id)) {
                System.out.println("Financiamento aprovado com sucesso!");
            } else {
                System.out.println("Erro ao aprovar financiamento!");
            }
        } else {
            System.out.println("Aprovação cancelada.");
        }
    }

    private void reprovarFinanciamento() {
        System.out.println("\n=== REPROVAR FINANCIAMENTO ===");
        System.out.print("ID do Financiamento: ");
        String id = scanner.nextLine();

        Financiamento financiamento = financeiroController.buscarPorId(id);
        if (financiamento == null) {
            System.out.println("Financiamento não encontrado!");
            return;
        }

        if (!financiamento.getStatus().equals("PENDENTE")) {
            System.out.println("Apenas financiamentos pendentes podem ser reprovados!");
            System.out.println("Status atual: " + financiamento.getStatus());
            return;
        }

        System.out.println("Financiamento encontrado:");
        System.out.println(financiamento);
        System.out.print("Confirma a reprovação? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s") || confirmacao.equalsIgnoreCase("sim")) {
            if (financeiroController.reprovarFinanciamento(id)) {
                System.out.println("Financiamento reprovado com sucesso!");
            } else {
                System.out.println("Erro ao reprovar financiamento!");
            }
        } else {
            System.out.println("Reprovação cancelada.");
        }
    }

    private void buscarFinanciamento() {
        System.out.println("\n=== BUSCAR FINANCIAMENTO ===");
        System.out.print("ID do Financiamento: ");
        String id = scanner.nextLine();

        Financiamento financiamento = financeiroController.buscarPorId(id);
        if (financiamento != null) {
            System.out.println("Financiamento encontrado:");
            System.out.println(financiamento);
            System.out.println("Cliente: " + financiamento.getVenda().getCliente().getNome());
            System.out.println("Veículo: " + financiamento.getVenda().getVeiculo().getMarca() + " " +
                    financiamento.getVenda().getVeiculo().getModelo());
            System.out.println("Total a pagar: R$ " + String.format("%.2f",
                    financiamento.getValorParcela() * financiamento.getNumeroParcelas()));
        } else {
            System.out.println("Financiamento não encontrado!");
        }
    }

    private void listarTodosFinanciamentos() {
        System.out.println("\n=== TODOS OS FINANCIAMENTOS ===");
        List<Financiamento> financiamentos = financeiroController.listarTodos();

        if (financiamentos.isEmpty()) {
            System.out.println("Nenhum financiamento cadastrado.");
        } else {
            for (Financiamento financiamento : financiamentos) {
                System.out.println(financiamento);
                System.out.println("  Cliente: " + financiamento.getVenda().getCliente().getNome());
                System.out.println("  Veículo: " + financiamento.getVenda().getVeiculo().getMarca() + " " +
                        financiamento.getVenda().getVeiculo().getModelo());
                System.out.println("---");
            }
        }
    }

    private void financiamentosPorStatus() {
        System.out.println("\n=== FINANCIAMENTOS POR STATUS ===");
        System.out.println("Status disponíveis: PENDENTE, APROVADO, REPROVADO");
        System.out.print("Digite o status: ");
        String status = scanner.nextLine().toUpperCase();

        if (!status.equals("PENDENTE") && !status.equals("APROVADO") && !status.equals("REPROVADO")) {
            System.out.println("Status inválido!");
            return;
        }

        List<Financiamento> financiamentos = financeiroController.buscarPorStatus(status);
        if (financiamentos.isEmpty()) {
            System.out.println("Nenhum financiamento encontrado com o status: " + status);
        } else {
            System.out.println("Financiamentos com status " + status + ":");
            for (Financiamento financiamento : financiamentos) {
                System.out.println(financiamento);
                System.out.println("  Cliente: " + financiamento.getVenda().getCliente().getNome());
                System.out.println("---");
            }
        }
    }

    private void relatorioFinanceiro() {
        System.out.println("\n=== RELATÓRIO FINANCEIRO ===");

        List<Financiamento> todosFinanciamentos = financeiroController.listarTodos();
        List<Financiamento> pendentes = financeiroController.buscarPorStatus("PENDENTE");
        List<Financiamento> aprovados = financeiroController.buscarPorStatus("APROVADO");
        List<Financiamento> reprovados = financeiroController.buscarPorStatus("REPROVADO");

        double totalFinanciado = financeiroController.calcularTotalFinanciado();

        System.out.println("=== RESUMO GERAL ===");
        System.out.println("Total de financiamentos: " + todosFinanciamentos.size());
        System.out.println("Financiamentos pendentes: " + pendentes.size());
        System.out.println("Financiamentos aprovados: " + aprovados.size());
        System.out.println("Financiamentos reprovados: " + reprovados.size());
        System.out.println("Valor total financiado (aprovados): R$ " + String.format("%.2f", totalFinanciado));

        if (!aprovados.isEmpty()) {
            double mediaFinanciamento = totalFinanciado / aprovados.size();
            System.out.println("Valor médio por financiamento: R$ " + String.format("%.2f", mediaFinanciamento));
        }

        // Taxa de aprovação
        if (todosFinanciamentos.size() > 0) {
            double taxaAprovacao = (aprovados.size() * 100.0) / todosFinanciamentos.size();
            System.out.println("Taxa de aprovação: " + String.format("%.1f", taxaAprovacao) + "%");
        }
    }

    private void simularFinanciamento() {
        System.out.println("\n=== SIMULAR FINANCIAMENTO ===");

        System.out.print("Valor a ser financiado: R$ ");
        double valorFinanciado = scanner.nextDouble();

        System.out.print("Número de parcelas: ");
        int numeroParcelas = scanner.nextInt();

        System.out.print("Taxa de juros mensal (%): ");
        double taxaJuros = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer

        // Calcular valor da parcela usando a fórmula de financiamento
        double jurosDecimal = taxaJuros / 100;
        double valorParcela = (valorFinanciado * jurosDecimal * Math.pow(1 + jurosDecimal, numeroParcelas)) /
                (Math.pow(1 + jurosDecimal, numeroParcelas) - 1);

        double totalAPagar = valorParcela * numeroParcelas;
        double totalJuros = totalAPagar - valorFinanciado;

        System.out.println("\n=== RESULTADO DA SIMULAÇÃO ===");
        System.out.println("Valor financiado: R$ " + String.format("%.2f", valorFinanciado));
        System.out.println("Número de parcelas: " + numeroParcelas);
        System.out.println("Taxa de juros: " + taxaJuros + "% a.m.");
        System.out.println("Valor da parcela: R$ " + String.format("%.2f", valorParcela));
        System.out.println("Total a pagar: R$ " + String.format("%.2f", totalAPagar));
        System.out.println("Total de juros: R$ " + String.format("%.2f", totalJuros));
        System.out.println("Percentual de juros: " + String.format("%.1f", (totalJuros / valorFinanciado) * 100) + "%");
    }
}