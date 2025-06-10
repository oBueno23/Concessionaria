package src.Model;

import java.time.LocalDate;

public class Venda {

    private String id;
    private Cliente cliente;
    private Vendedor vendedor;
    private Veiculo veiculo;
    private LocalDate dataVenda;
    private double valor;
    private String formaPagamento;
    private Financiamento financiamento;

    public Venda(String id,Cliente cliente, Vendedor vendedor, Veiculo veiculo, double valor, String formaPagamento){
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.veiculo = veiculo;
        this.dataVenda = LocalDate.now();
        this.valor = valor;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Vendedor getVendedor() { return vendedor; }
    public void setVendedor(Vendedor vendedor) { this.vendedor = vendedor; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public LocalDate getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDate dataVenda) { this.dataVenda = dataVenda; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public Financiamento getFinanciamento() { return financiamento; }
    public void setFinanciamento(Financiamento financiamento) { this.financiamento = financiamento; }

    @Override
    public String toString(){
        return "Venda ID: " + id + " - Cliente: " + cliente.getNome() +
                " - Veículo: " + veiculo.getMarca() + " " + veiculo.getModelo() +
                " - Valor: R$ " + valor + " - Data: " + dataVenda;
    }

}
