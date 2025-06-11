package src.Controller;

import src.Model.Carro;
import src.Model.Estoque;
import src.Model.Moto;
import src.Model.Veiculo;

import java.util.List;



public class VeiculoController {
    private Estoque estoque;

    public VeiculoController(){
        this.estoque = new Estoque();
    }

    public boolean cadastrarCarro(String chassi, String marca, String modelo, int ano, String cor, double preco, int numeroPortas, String tipoCombustivel, boolean arCondicionado){
        if (estoque.buscarPorChassi(chassi) != null){
            return false;
        }
        Carro carro = new Carro(chassi, marca, modelo, ano, cor, preco, numeroPortas, tipoCombustivel, arCondicionado);
        estoque.adicionarVeiculo(carro);
        return true;
    }

    public boolean cadastrarMoto(String chassi, String marca, String modelo, int ano, String cor, double preco,
                                 int cilindrada, String tipoPartida, boolean bagageiro) {
        if (estoque.buscarPorChassi(chassi) != null) {
            return false; // Veículo já existe
        }

        Moto moto = new Moto(chassi, marca, modelo, ano, cor, preco, cilindrada, tipoPartida, bagageiro);
        estoque.adicionarVeiculo(moto);
        return true;
    }

    public Veiculo buscarPorChassi(String chassi) {
        return estoque.buscarPorChassi(chassi);
    }

    public List<Veiculo> buscarPorMarca(String marca) {
        return estoque.buscarPorMarca(marca);
    }

    public List<Veiculo> listarDisponiveis() {
        return estoque.listarDisponiveis();
    }

    public List<Veiculo> listarTodos() {
        return estoque.listarTodos();
    }

    public boolean removerVeiculo(String chassi) {
        return estoque.removerVeiculo(chassi);
    }

    public boolean marcarComoVendido(String chassi) {
        Veiculo veiculo = estoque.buscarPorChassi(chassi);
        if (veiculo != null) {
            veiculo.setDisponivel(false);
            return true;
        }
        return false;
    }

    public Estoque getEstoque() {
        return estoque;
    }


}