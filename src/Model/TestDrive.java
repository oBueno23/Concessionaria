package src.Model;

import java.time.LocalDateTime;

public class TestDrive {
    private String ID;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataHora;
    private int duracaoMinutos;
    private String status;
    private String observacoes;

    public TestDrive(String ID, Cliente cliente, Veiculo veiculo, LocalDateTime dataHora, int duracaoMinutos) {
        this.ID = ID;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataHora = dataHora;
        this.duracaoMinutos = duracaoMinutos;
        this.status = "AGENDADO";
    }

    public String getId() { return ID; }
    public void setId(String ID) { this.ID = this.ID; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    @Override
    public String toString(){
        return "Test Drive ID: " + ID + " - Cliente: " + cliente.getNome() +
                " - Veículo: " + veiculo.getMarca() + " " + veiculo.getModelo() +
                " - Data/Hora: " + dataHora + " - Status: " + status;
    }

}