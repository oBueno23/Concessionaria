package src.Controller;

import src.Model.Cliente;
import src.Model.Veiculo;
import src.Model.Venda;
import src.Model.Vendedor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VendaController {
    private List<Venda> vendas;

    public VendaController() {
        this.vendas = new ArrayList<>();
    }

    public String realizarVenda(Cliente cliente, Vendedor vendedor, Veiculo veiculo, String formaPagamento) {
        if (!veiculo.isDisponivel()) {
            return null; // Veículo não disponível
        }

        String id = UUID.randomUUID().toString();
        Venda venda = new Venda(id, cliente, vendedor,veiculo, veiculo.getPreco(),formaPagamento);

        vendas.add(venda);
        veiculo.setDisponivel(false);
        cliente.adicionarCompra(venda);
        vendedor.adicionarVenda(venda);

        return id;
    }

    public Venda buscarPorId(String id) {
        return vendas.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Venda> listarTodas() {
        return new ArrayList<>(vendas);
    }

    public List<Venda> buscarPorCliente(Cliente cliente) {
        return vendas.stream()
                .filter(v -> v.getCliente().equals(cliente))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public List<Venda> buscarPorVendedor(Vendedor vendedor) {
        return vendas.stream()
                .filter(v -> v.getVendedor().equals(vendedor))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public double calcularFaturamentoTotal() {
        return vendas.stream().mapToDouble(Venda::getValor).sum();
    }

}