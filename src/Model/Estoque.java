package src.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Estoque {
    private List<Veiculo> veiculos;

    public Estoque() {
        this.veiculos = new ArrayList<>();
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public boolean removerVeiculo(String chassi) {
        return veiculos.removeIf(v -> v.getChassi().equals(chassi));
    }

    public Veiculo buscarPorChassi(String chassi) {
        return veiculos.stream()
                .filter(v -> v.getChassi().equals(chassi))
                .findFirst()
                .orElse(null);
    }

    public List<Veiculo> buscarPorMarca(String marca) {
        return veiculos.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase(marca))
                .collect(Collectors.toList());
    }

    public List<Veiculo> listarDisponiveis() {
        return veiculos.stream()
                .filter(Veiculo::isDisponivel)
                .collect(Collectors.toList());
    }

    public List<Veiculo> listarTodos() {
        return new ArrayList<>(veiculos);
    }

    public int quantidadeTotal() {
        return veiculos.size();
    }

    public int quantidadeDisponiveis() {
        return (int) veiculos.stream().filter(Veiculo::isDisponivel).count();
    }
}