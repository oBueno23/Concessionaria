package src.View;

import src.Controller.ClienteController;
import src.Controller.TestDriveController;
import src.Controller.VeiculoController;
import src.Model.Cliente;
import src.Model.TestDrive;
import src.Model.Veiculo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TestDriveView {
    private TestDriveController testDriveController;
    private ClienteController clienteController;
    private VeiculoController veiculoController;
    private Scanner scanner;

    public TestDriveView(TestDriveController testDriveController, ClienteController clienteController, VeiculoController veiculoController) {
        this.testDriveController = testDriveController;
        this.clienteController = clienteController;
        this.veiculoController = veiculoController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR TEST DRIVES ===");
            System.out.println("1. Agendar Test Drive");
            System.out.println("2. Realizar Test Drive");
            System.out.println("3. Cancelar Test Drive");
            System.out.println("4. Buscar Test Drive por ID");
            System.out.println("5. Listar Todos os Test Drives");
            System.out.println("6. Test Drives por Cliente");
            System.out.println("7. Test Drives por Status");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    agendarTestDrive();
                    break;
                case 2:
                    realizarTestDrive();
                    break;
                case 3:
                    cancelarTestDrive();
                    break;
                case 4:
                    buscarTestDrive();
                    break;
                case 5:
                    listarTodosTestDrives();
                    break;
                case 6:
                    testDrivesPorCliente();
                    break;
                case 7:
                    testDrivesPorStatus();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void agendarTestDrive() {
        System.out.println("\n=== AGENDAR TEST DRIVE ===");

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
            System.out.println("Veículo não está disponível para test drive!");
            return;
        }

        System.out.print("Data e hora (formato: dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dataHora = LocalDateTime.parse(dataHoraStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data/hora inválido!");
            return;
        }

        System.out.print("Duração em minutos: ");
        int duracaoMinutos = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        String idTestDrive = testDriveController.agendarTestDrive(cliente, veiculo, dataHora, duracaoMinutos);
        if (idTestDrive != null) {
            System.out.println("Test Drive agendado com sucesso!");
            System.out.println("ID do Test Drive: " + idTestDrive);
        } else {
            System.out.println("Erro ao agendar test drive!");
        }
    }

    private void realizarTestDrive() {
        System.out.println("\n=== REALIZAR TEST DRIVE ===");
        System.out.print("ID do Test Drive: ");
        String id = scanner.nextLine();

        TestDrive testDrive = testDriveController.buscarPorId(id);
        if (testDrive == null) {
            System.out.println("Test Drive não encontrado!");
            return;
        }

        if (!testDrive.getStatus().equals("AGENDADO")) {
            System.out.println("Test Drive não está com status AGENDADO!");
            return;
        }

        System.out.print("Observações sobre o test drive: ");
        String observacoes = scanner.nextLine();

        if (testDriveController.realizarTestDrive(id, observacoes)) {
            System.out.println("Test Drive realizado com sucesso!");
        } else {
            System.out.println("Erro ao realizar test drive!");
        }
    }

    private void cancelarTestDrive() {
        System.out.println("\n=== CANCELAR TEST DRIVE ===");
        System.out.print("ID do Test Drive: ");
        String id = scanner.nextLine();

        TestDrive testDrive = testDriveController.buscarPorId(id);
        if (testDrive == null) {
            System.out.println("Test Drive não encontrado!");
            return;
        }

        if (!testDrive.getStatus().equals("AGENDADO")) {
            System.out.println("Apenas test drives agendados podem ser cancelados!");
            return;
        }

        if (testDriveController.cancelarTestDrive(id)) {
            System.out.println("Test Drive cancelado com sucesso!");
        } else {
            System.out.println("Erro ao cancelar test drive!");
        }
    }

    private void buscarTestDrive() {
        System.out.println("\n=== BUSCAR TEST DRIVE ===");
        System.out.print("ID do Test Drive: ");
        String id = scanner.nextLine();

        TestDrive testDrive = testDriveController.buscarPorId(id);
        if (testDrive != null) {
            System.out.println("Test Drive encontrado:");
            System.out.println(testDrive);
            if (testDrive.getObservacoes() != null && !testDrive.getObservacoes().isEmpty()) {
                System.out.println("Observações: " + testDrive.getObservacoes());
            }
        } else {
            System.out.println("Test Drive não encontrado!");
        }
    }

    private void listarTodosTestDrives() {
        System.out.println("\n=== TODOS OS TEST DRIVES ===");
        List<TestDrive> testDrives = testDriveController.listarTodos();

        if (testDrives.isEmpty()) {
            System.out.println("Nenhum test drive cadastrado.");
        } else {
            for (TestDrive testDrive : testDrives) {
                System.out.println(testDrive);
                if (testDrive.getObservacoes() != null && !testDrive.getObservacoes().isEmpty()) {
                    System.out.println("  Observações: " + testDrive.getObservacoes());
                }
                System.out.println("---");
            }
        }
    }

    private void testDrivesPorCliente() {
        System.out.println("\n=== TEST DRIVES POR CLIENTE ===");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteController.buscarPorId(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        List<TestDrive> testDrives = testDriveController.buscarPorCliente(cliente);
        if (testDrives.isEmpty()) {
            System.out.println("Nenhum test drive encontrado para este cliente.");
        } else {
            System.out.println("Test Drives do cliente " + cliente.getNome() + ":");
            for (TestDrive testDrive : testDrives) {
                System.out.println(testDrive);
                if (testDrive.getObservacoes() != null && !testDrive.getObservacoes().isEmpty()) {
                    System.out.println("  Observações: " + testDrive.getObservacoes());
                }
                System.out.println("---");
            }
        }
    }

    private void testDrivesPorStatus() {
        System.out.println("\n=== TEST DRIVES POR STATUS ===");
        System.out.println("Status disponíveis: AGENDADO, REALIZADO, CANCELADO");
        System.out.print("Digite o status: ");
        String status = scanner.nextLine().toUpperCase();

        if (!status.equals("AGENDADO") && !status.equals("REALIZADO") && !status.equals("CANCELADO")) {
            System.out.println("Status inválido!");
            return;
        }

        List<TestDrive> testDrives = testDriveController.buscarPorStatus(status);
        if (testDrives.isEmpty()) {
            System.out.println("Nenhum test drive encontrado com o status: " + status);
        } else {
            System.out.println("Test Drives com status " + status + ":");
            for (TestDrive testDrive : testDrives) {
                System.out.println(testDrive);
                if (testDrive.getObservacoes() != null && !testDrive.getObservacoes().isEmpty()) {
                    System.out.println("  Observações: " + testDrive.getObservacoes());
                }
                System.out.println("---");
            }
        }
    }
}
