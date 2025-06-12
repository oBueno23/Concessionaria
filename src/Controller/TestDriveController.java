package src.Controller;

import src.Model.Cliente;
import src.Model.TestDrive;
import src.Model.Veiculo;
import src.Util.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDriveController {
    private List<TestDrive> testDrives;

    public TestDriveController() {
        this.testDrives = new ArrayList<>();
    }

    public String agendarTestDrive(Cliente cliente, Veiculo veiculo, LocalDateTime dataHora, int duracaoMinutos) {
        if (!veiculo.isDisponivel()) {
            return null; // Veículo não disponível
        }

        String id = UUID.randomUUID().toString();
        TestDrive testDrive = new TestDrive(id, cliente, veiculo, dataHora, duracaoMinutos);
        testDrives.add(testDrive);
        Logger.log("Test drive agendado: " + testDrive);
        return id;
    }

    public boolean realizarTestDrive(String id, String observacoes) {
        TestDrive testDrive = buscarPorId(id);
        if (testDrive != null && testDrive.getStatus().equals("AGENDADO")) {
            testDrive.setStatus("REALIZADO");
            testDrive.setObservacoes(observacoes);
            Logger.log("Test drive realizado: " + testDrive);
            return true;
        }
        return false;
    }

    public boolean cancelarTestDrive(String id) {
        TestDrive testDrive = buscarPorId(id);
        if (testDrive != null && testDrive.getStatus().equals("AGENDADO")) {
            testDrive.setStatus("CANCELADO");
            Logger.log("Test drive cancelado: " + testDrive);
            return true;
        }
        return false;
    }

    public TestDrive buscarPorId(String id) {
        return testDrives.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<TestDrive> listarTodos() {
        return new ArrayList<>(testDrives);
    }

    public List<TestDrive> buscarPorCliente(Cliente cliente) {
        return testDrives.stream()
                .filter(t -> t.getCliente().equals(cliente))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public List<TestDrive> buscarPorStatus(String status) {
        return testDrives.stream()
                .filter(t -> t.getStatus().equals(status))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
